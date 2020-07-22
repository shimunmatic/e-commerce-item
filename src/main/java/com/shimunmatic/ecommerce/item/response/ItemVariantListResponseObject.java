package com.shimunmatic.ecommerce.item.response;

import com.shimunmatic.ecommerce.item.dto.ItemVariantDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItemVariantListResponseObject extends ResponseObject<Iterable<ItemVariantDTO>> {

    public ItemVariantListResponseObject(Iterable<ItemVariantDTO> data, String errorMessage, boolean success) {
        super(data, errorMessage, success);
    }

    public static ItemVariantListResponseObject ofData(Iterable<ItemVariantDTO> data) {
        return new ItemVariantListResponseObject(data, null, true);
    }

    public static ItemVariantListResponseObject ofErrorMessage(String errorMessage) {
        return new ItemVariantListResponseObject(null, errorMessage, false);
    }
}
