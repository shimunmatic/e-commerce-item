package com.shimunmatic.ecommerce.item.service.definition;

import com.shimunmatic.ecommerce.item.dto.ItemVariantDTO;
import com.shimunmatic.ecommerce.item.exception.ResourceNotFoundException;
import com.shimunmatic.ecommerce.item.model.ItemVariant;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemVariantService extends CRUDService<ItemVariant, Long> {

    List<ItemVariant> getVariantsForItem(Long itemId);

    List<ItemVariantDTO> getVariantsForItemDto(Long itemId);

    ItemVariantDTO saveDto(Long itemId, ItemVariantDTO dto);

    ItemVariantDTO getDto(Long itemId, Long variantId) throws ResourceNotFoundException;

    void uploadThumbnail(Long itemVariantId, MultipartFile file) throws IOException;

    ItemVariantDTO update(Long variantId, ItemVariantDTO itemVariantDTO);

}
