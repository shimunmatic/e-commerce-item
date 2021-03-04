package com.shimunmatic.ecommerce.item.converter;

import com.shimunmatic.ecommerce.item.dto.ItemMediaDTO;
import com.shimunmatic.ecommerce.item.model.ItemMedia;
import org.springframework.stereotype.Component;

@Component
public class ItemMediaConverter implements Converter<ItemMedia, ItemMediaDTO> {

    @Override
    public ItemMediaDTO toDto(ItemMedia item) {
        if (item == null) { return null; }
        return ItemMediaDTO.builder().id(item.getId()).name(item.getName()).path(item.getPath()).itemId(item.getItemId()).type(item.getType()).build();
    }

    @Override
    public ItemMedia toModel(ItemMediaDTO itemDTO) {
        if (itemDTO == null) { return null; }
        ItemMedia item = new ItemMedia(itemDTO.getName(), itemDTO.getPath(), itemDTO.getType(), itemDTO.getItemId(), null);
        item.setId(itemDTO.getId());
        return item;
    }
}
