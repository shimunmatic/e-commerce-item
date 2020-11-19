package com.shimunmatic.ecommerce.item.service.definition;

import com.shimunmatic.ecommerce.item.dto.ItemDTO;
import com.shimunmatic.ecommerce.item.model.Item;

import java.util.List;

public interface ItemService extends CRUDService<Item, Long> {

    List<ItemDTO> getItemsForCategoryDto(Long categoryId);

    ItemDTO getDetailsDto(Long itemId);


}
