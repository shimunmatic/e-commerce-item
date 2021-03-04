package com.shimunmatic.ecommerce.item.service.implementation;

import com.shimunmatic.ecommerce.item.converter.Converter;
import com.shimunmatic.ecommerce.item.dto.ItemVariantDTO;
import com.shimunmatic.ecommerce.item.dto.UploadResult;
import com.shimunmatic.ecommerce.item.exception.ResourceNotFoundException;
import com.shimunmatic.ecommerce.item.model.ItemVariant;
import com.shimunmatic.ecommerce.item.repository.ItemVariantRepository;
import com.shimunmatic.ecommerce.item.response.ResponseObject;
import com.shimunmatic.ecommerce.item.service.definition.ItemVariantService;
import com.shimunmatic.ecommerce.item.util.CDNUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemVariantServiceImpl extends AbstractService<ItemVariant, Long> implements ItemVariantService {
    private final ItemVariantRepository itemVariantRepository;
    private final Converter<ItemVariant, ItemVariantDTO> converter;
    private final String CDN_URL;

    public ItemVariantServiceImpl(ItemVariantRepository repository, Converter<ItemVariant, ItemVariantDTO> converter, @Value("${ecommerce.cdn-url}") String cdnUrl) {
        super(repository);
        this.itemVariantRepository = repository;
        this.converter = converter;
        this.CDN_URL = cdnUrl;
    }

    @Override
    public List<ItemVariant> getVariantsForItem(Long itemId) {
        return itemVariantRepository.findAllByItemId(itemId);
    }

    @Override
    public List<ItemVariantDTO> getVariantsForItemDto(Long itemId) {
        return converter.toDto(getVariantsForItem(itemId));
    }

    @Override
    public ItemVariantDTO saveDto(Long itemId, ItemVariantDTO dto) {
        dto.setItemId(itemId);
        log.info("Saving item variant: {}", dto);
        return converter.toDto(save(converter.toModel(dto)));
    }

    @Override
    public ItemVariantDTO getDto(Long itemId, Long variantId) throws ResourceNotFoundException {
        Optional<ItemVariant> ov = itemVariantRepository.findOneByItemIdAndId(itemId, variantId);
        if (ov.isEmpty()) {
            log.info("ItemVariant not found for ids: {} {}", itemId, variantId);
            throw new ResourceNotFoundException(String.format("No variant of an item (%s) found matching id %s", itemId, variantId));
        }
        return converter.toDto(ov.get());
    }

    @Override
    public void uploadThumbnail(Long itemVariantId, MultipartFile file) throws IOException {
        ResponseObject<UploadResult> responseObject = CDNUploadUtil.uploadMediaToCdn(String.format("%s/api/media/v1/media/item-variants/%d?thumbnail=true", CDN_URL, itemVariantId), file);
        if (responseObject != null && responseObject.isSuccess()) {
            UploadResult ur = responseObject.getData();
            itemVariantRepository.updateThumbnail(itemVariantId, ur.getPath());
        } else {
            log.info("Error while saving thumbnail: {}", responseObject);
        }
    }

    @Override
    public ItemVariantDTO update(Long variantId, ItemVariantDTO itemVariantDTO) {
        itemVariantDTO.setItemId(variantId);
        return converter.toDto(update(converter.toModel(itemVariantDTO)));
    }
}
