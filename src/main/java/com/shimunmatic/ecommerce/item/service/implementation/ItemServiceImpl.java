package com.shimunmatic.ecommerce.item.service.implementation;

import com.shimunmatic.ecommerce.item.converter.Converter;
import com.shimunmatic.ecommerce.item.dto.*;
import com.shimunmatic.ecommerce.item.exception.ResourceNotFoundException;
import com.shimunmatic.ecommerce.item.model.Item;
import com.shimunmatic.ecommerce.item.model.ItemMedia;
import com.shimunmatic.ecommerce.item.model.ItemVariant;
import com.shimunmatic.ecommerce.item.repository.ItemMediaRepository;
import com.shimunmatic.ecommerce.item.repository.ItemRepository;
import com.shimunmatic.ecommerce.item.response.ResponseObject;
import com.shimunmatic.ecommerce.item.service.definition.ItemService;
import com.shimunmatic.ecommerce.item.service.definition.ItemVariantService;
import com.shimunmatic.ecommerce.item.util.CDNUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemServiceImpl extends AbstractService<Item, Long> implements ItemService {
    private final String CDN_URL;
    private final int PAGE_DEFAULT;
    private final int PAGE_DEFAULT_SIZE;
    private final ItemRepository itemRepository;
    private final ItemMediaRepository itemMediaRepository;
    private final ItemVariantService itemVariantService;
    private final Converter<Item, ItemDTO> itemConverter;
    private final Converter<ItemVariant, ItemVariantDTO> itemVariantConverter;
    private final RestTemplate restTemplate = new RestTemplate();

    public ItemServiceImpl(ItemRepository repository, ItemVariantService itemVariantService, Converter<Item, ItemDTO> itemConverter, Converter<ItemVariant, ItemVariantDTO> itemVariantConverter,
                           @Value("${ecommerce.cdn-url}") String cdnUrl, @Value("${pageable.default.page}") int defaultPage, @Value("${pageable.default.page-size}") int defaultPageSize,
                           ItemMediaRepository itemMediaRepository) {
        super(repository);
        this.itemRepository = repository;
        this.itemVariantService = itemVariantService;
        this.itemConverter = itemConverter;
        this.itemVariantConverter = itemVariantConverter;
        this.PAGE_DEFAULT = defaultPage;
        this.PAGE_DEFAULT_SIZE = defaultPageSize;
        this.itemMediaRepository = itemMediaRepository;
        this.CDN_URL = cdnUrl;
    }

    @Override
    public List<ItemDTO> getItemsForCategoryDto(Long categoryId) {
        return itemConverter.toDto(itemRepository.findAllByCategoryId(categoryId));
    }

    @Override
    public ItemDTO getDetailsDto(Long itemId) throws ResourceNotFoundException {
        Optional<Item> oi = getById(itemId);
        if (oi.isEmpty()) {
            log.info("Item resource not found: {}", itemId);
            throw new ResourceNotFoundException("No item found matching ID: " + itemId);
        }

        List<ItemVariant> variants = itemVariantService.getVariantsForItem(itemId);
        ItemDTO item = itemConverter.toDto(oi.get());
        item.setVariants(itemVariantConverter.toDto(variants));

        return item;
    }

    @Override
    public List<ItemDTO> getAllDto() {
        return itemConverter.toDto(getAll());
    }

    @Override
    public ItemDTO saveDto(ItemDTO dto) {
        return itemConverter.toDto(save(itemConverter.toModel(dto)));
    }

    @Override
    public Page<ItemDTO> getAll(ItemCriteria itemCriteria) {
        Pageable pageable = getPageableFromCriteria(itemCriteria);
        Page<Item> page = itemCriteria.getCategoryId() != null ? itemRepository.findAllByCategoryId(itemCriteria.getCategoryId(), pageable) : itemRepository.findAll(pageable);
        return page.map(itemConverter::toDto);
    }

    @Override
    public void uploadFile(Long itemId, ItemMediaType mediaType, MultipartFile file) throws IOException {
        ResponseObject<UploadResult> responseObject = CDNUploadUtil.uploadMediaToCdn(String.format("%s/api/media/v1/media/items/%d", CDN_URL, itemId), file);

        if (responseObject != null && responseObject.isSuccess()) {
            UploadResult ur = responseObject.getData();
            ItemMedia itemMedia = ItemMedia.builder().itemId(itemId).name(ur.getFileName()).path(ur.getPath()).type(mediaType.name()).build();
            itemMediaRepository.save(itemMedia);
        } else {
            log.info("Error while saving thumbnail: {}", responseObject);
        }
    }

    @Override
    public void uploadThumbnail(Long itemId, MultipartFile file) throws IOException {
        ResponseObject<UploadResult> responseObject = CDNUploadUtil.uploadMediaToCdn(String.format("%s/api/media/v1/media/items/%d?thumbnail=true", CDN_URL, itemId), file);
        if (responseObject != null && responseObject.isSuccess()) {
            UploadResult ur = responseObject.getData();
            itemRepository.updateThumbnail(itemId, ur.getPath());
        } else {
            log.info("Error while saving thumbnail: {}", responseObject);
        }
    }

    @Override
    public ItemDTO update(Long itemId, ItemDTO itemDTO) {
        itemDTO.setId(itemId);
        return itemConverter.toDto(update(itemConverter.toModel(itemDTO)));
    }


    private Pageable getPageableFromCriteria(ItemCriteria itemCriteria) {
        Sort sort = getSortFromCriteria(itemCriteria);
        if (itemCriteria.getPage() == null && itemCriteria.getPageSize() == null) {
            return PageRequest.of(PAGE_DEFAULT, PAGE_DEFAULT_SIZE, sort);
        }
        return PageRequest.of(itemCriteria.getPage(), itemCriteria.getPageSize(), sort);
    }

    private Sort getSortFromCriteria(ItemCriteria itemCriteria) {
        if (StringUtils.isEmpty(itemCriteria.getSortField())) { return Sort.unsorted(); }
        return Sort.by(itemCriteria.getSortDirection() != null ? itemCriteria.getSortDirection() : Sort.Direction.ASC, itemCriteria.getSortField());
    }
}
