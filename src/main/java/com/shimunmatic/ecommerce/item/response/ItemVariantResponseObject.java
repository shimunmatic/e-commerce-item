package com.shimunmatic.ecommerce.item.response;

import com.shimunmatic.ecommerce.item.dto.ItemDTO;
import com.shimunmatic.ecommerce.item.dto.ItemVariantDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItemVariantResponseObject extends ResponseObject<ItemVariantDTO> {

    public ItemVariantResponseObject(ItemVariantDTO data, String errorMessage, boolean success) {
        super(data, errorMessage, success);
    }

    public static ItemVariantResponseObject ofData(ItemVariantDTO data) {
        return new ItemVariantResponseObject(data, null, true);
    }

    public static ItemVariantResponseObject ofErrorMessage(String errorMessage) {
        return new ItemVariantResponseObject(null, errorMessage, false);
    }
}
