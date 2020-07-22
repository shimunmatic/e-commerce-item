package com.shimunmatic.ecommerce.item.response;

import com.shimunmatic.ecommerce.item.dto.DiscountDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DiscountResponseObject extends ResponseObject<DiscountDTO> {

    public DiscountResponseObject(DiscountDTO data, String errorMessage, boolean success) {
        super(data, errorMessage, success);
    }

    public static DiscountResponseObject ofData(DiscountDTO data) {
        return new DiscountResponseObject(data, null, true);
    }

    public static DiscountResponseObject ofErrorMessage(String errorMessage) {
        return new DiscountResponseObject(null, errorMessage, false);
    }
}
