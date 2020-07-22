package com.shimunmatic.ecommerce.item.response;

import com.shimunmatic.ecommerce.item.dto.ItemDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItemResponseObject extends ResponseObject<ItemDTO> {

    public ItemResponseObject(ItemDTO data, String errorMessage, boolean success) {
        super(data, errorMessage, success);
    }

    public static ItemResponseObject ofData(ItemDTO data) {
        return new ItemResponseObject(data, null, true);
    }

    public static ItemResponseObject ofErrorMessage(String errorMessage) {
        return new ItemResponseObject(null, errorMessage, false);
    }
}
