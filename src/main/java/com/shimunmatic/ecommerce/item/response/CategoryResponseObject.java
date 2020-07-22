package com.shimunmatic.ecommerce.item.response;

import com.shimunmatic.ecommerce.item.dto.CategoryDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryResponseObject extends ResponseObject<CategoryDTO> {

    public CategoryResponseObject(CategoryDTO data, String errorMessage, boolean success) {
        super(data, errorMessage, success);
    }

    public static CategoryResponseObject ofData(CategoryDTO data) {
        return new CategoryResponseObject(data, null, true);
    }

    public static CategoryResponseObject ofErrorMessage(String errorMessage) {
        return new CategoryResponseObject(null, errorMessage, false);
    }
}
