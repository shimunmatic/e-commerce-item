package com.shimunmatic.ecommerce.item.service.definition;

import com.shimunmatic.ecommerce.item.dto.ItemCriteria;
import com.shimunmatic.ecommerce.item.dto.ItemDTO;
import com.shimunmatic.ecommerce.item.exception.ResourceNotFoundException;
import com.shimunmatic.ecommerce.item.model.Item;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService extends CRUDService<Item, Long> {

    List<ItemDTO> getItemsForCategoryDto(Long categoryId);

    ItemDTO getDetailsDto(Long itemId) throws ResourceNotFoundException;

    List<ItemDTO> getAllDto();

    ItemDTO saveDto(ItemDTO dto);

    Page<ItemDTO> getAll(ItemCriteria itemCriteria);
}
