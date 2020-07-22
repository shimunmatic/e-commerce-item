package com.shimunmatic.ecommerce.item.response;

import com.shimunmatic.ecommerce.item.dto.DiscountDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DiscountListResponseObject extends ResponseObject<Iterable<DiscountDTO>> {

    public DiscountListResponseObject(Iterable<DiscountDTO> data, String errorMessage, boolean success) {
        super(data, errorMessage, success);
    }

    public static DiscountListResponseObject ofData(Iterable<DiscountDTO> data) {
        return new DiscountListResponseObject(data, null, true);
    }

    public static DiscountListResponseObject ofErrorMessage(String errorMessage) {
        return new DiscountListResponseObject(null, errorMessage, false);
    }
}
