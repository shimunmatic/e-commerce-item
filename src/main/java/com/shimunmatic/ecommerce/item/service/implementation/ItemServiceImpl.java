package com.shimunmatic.ecommerce.item.service.implementation;

import com.shimunmatic.ecommerce.item.converter.Converter;
import com.shimunmatic.ecommerce.item.dto.ItemCriteria;
import com.shimunmatic.ecommerce.item.dto.ItemDTO;
import com.shimunmatic.ecommerce.item.dto.ItemVariantDTO;
import com.shimunmatic.ecommerce.item.exception.ResourceNotFoundException;
import com.shimunmatic.ecommerce.item.model.Item;
import com.shimunmatic.ecommerce.item.model.ItemVariant;
import com.shimunmatic.ecommerce.item.repository.ItemRepository;
import com.shimunmatic.ecommerce.item.service.definition.ItemService;
import com.shimunmatic.ecommerce.item.service.definition.ItemVariantService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemServiceImpl extends AbstractService<Item, Long> implements ItemService {
    private final int PAGE_DEFAULT;
    private final int PAGE_DEFAULT_SIZE;
    private final ItemRepository itemRepository;
    private final ItemVariantService itemVariantService;
    private final Converter<Item, ItemDTO> itemConverter;
    private final Converter<ItemVariant, ItemVariantDTO> itemVariantConverter;


    public ItemServiceImpl(ItemRepository repository, ItemVariantService itemVariantService, Converter<Item, ItemDTO> itemConverter, Converter<ItemVariant, ItemVariantDTO> itemVariantConverter,
                           @Value("${pageable.default.page}") int defaultPage, @Value("${pageable.default.page-size}") int defaultPageSize) {
        super(repository);
        this.itemRepository = repository;
        this.itemVariantService = itemVariantService;
        this.itemConverter = itemConverter;
        this.itemVariantConverter = itemVariantConverter;
        this.PAGE_DEFAULT = defaultPage;
        this.PAGE_DEFAULT_SIZE = defaultPageSize;
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
