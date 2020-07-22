package com.shimunmatic.ecommerce.item.response;

import com.shimunmatic.ecommerce.item.dto.ItemDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItemListResponseObject extends ResponseObject<Iterable<ItemDTO>> {

    public ItemListResponseObject(Iterable<ItemDTO> data, String errorMessage, boolean success) {
        super(data, errorMessage, success);
    }

    public static ItemListResponseObject ofData(Iterable<ItemDTO> data) {
        return new ItemListResponseObject(data, null, true);
    }

    public static ItemListResponseObject ofErrorMessage(String errorMessage) {
        return new ItemListResponseObject(null, errorMessage, false);
    }
}
