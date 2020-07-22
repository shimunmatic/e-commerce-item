package com.shimunmatic.ecommerce.item.converter;

import com.shimunmatic.ecommerce.item.dto.ItemDTO;
import com.shimunmatic.ecommerce.item.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter implements Converter<Item, ItemDTO> {

    @Override
    public ItemDTO toDto(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .categoryId(item.getCategoryId())
                .basePrice(item.getBasePrice())
                .build();
    }

    @Override
    public Item toModel(ItemDTO itemDTO) {
        Item item = new Item(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getCategoryId(), itemDTO.getBasePrice());
        item.setId(itemDTO.getId());

        return item;
    }
}
