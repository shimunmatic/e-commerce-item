package com.shimunmatic.ecommerce.item.converter;

import com.shimunmatic.ecommerce.item.dto.ItemDTO;
import com.shimunmatic.ecommerce.item.dto.ItemMediaDTO;
import com.shimunmatic.ecommerce.item.dto.ItemVariantDTO;
import com.shimunmatic.ecommerce.item.model.Item;
import com.shimunmatic.ecommerce.item.model.ItemMedia;
import com.shimunmatic.ecommerce.item.model.ItemVariant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter implements Converter<Item, ItemDTO> {
    private final Converter<ItemVariant, ItemVariantDTO> itemVariantItemVariantDTOConverter;
    private final Converter<ItemMedia, ItemMediaDTO> itemMediaItemMediaDTOConverter;

    @Autowired
    public ItemConverter(Converter<ItemVariant, ItemVariantDTO> itemVariantItemVariantDTOConverter, Converter<ItemMedia, ItemMediaDTO> itemMediaItemMediaDTOConverter) {
        this.itemVariantItemVariantDTOConverter = itemVariantItemVariantDTOConverter;
        this.itemMediaItemMediaDTOConverter = itemMediaItemMediaDTOConverter;
    }

    @Override
    public ItemDTO toDto(Item item) {
        if (item == null) { return null; }
        return ItemDTO.builder().id(item.getId()).name(item.getName()).description(item.getDescription()).thumbnail(item.getThumbnail()).categoryId(item.getCategoryId()).basePrice(item.getBasePrice())
                      .variants(itemVariantItemVariantDTOConverter.toDto(item.getVariants())).itemMedia(itemMediaItemMediaDTOConverter.toDto(item.getItemMedia())).build();
    }

    @Override
    public Item toModel(ItemDTO itemDTO) {
        if (itemDTO == null) { return null; }
        Item item = new Item(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getThumbnail(), itemDTO.getCategoryId(), itemDTO.getBasePrice(),
                itemVariantItemVariantDTOConverter.toModel(itemDTO.getVariants()), itemMediaItemMediaDTOConverter.toModel(itemDTO.getItemMedia()));
        item.setId(itemDTO.getId());
        return item;
    }
}
