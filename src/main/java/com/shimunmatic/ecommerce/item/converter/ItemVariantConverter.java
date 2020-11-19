package com.shimunmatic.ecommerce.item.converter;

import com.shimunmatic.ecommerce.item.dto.ItemVariantDTO;
import com.shimunmatic.ecommerce.item.model.ItemVariant;
import org.springframework.stereotype.Component;

@Component
public class ItemVariantConverter implements Converter<ItemVariant, ItemVariantDTO> {

    @Override
    public ItemVariantDTO toDto(ItemVariant item) {
        if (item == null) { return null; }
        return ItemVariantDTO.builder().id(item.getId()).name(item.getName()).description(item.getDescription()).itemId(item.getItemId()).basePrice(item.getBasePrice()).build();
    }

    @Override
    public ItemVariant toModel(ItemVariantDTO itemDTO) {
        if (itemDTO == null) { return null; }
        ItemVariant item = new ItemVariant(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getItemId(), itemDTO.getBasePrice(), null);
        item.setId(itemDTO.getId());
        return item;
    }
}
