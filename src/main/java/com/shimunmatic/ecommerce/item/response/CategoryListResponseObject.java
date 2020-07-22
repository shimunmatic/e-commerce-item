package com.shimunmatic.ecommerce.item.response;

import com.shimunmatic.ecommerce.item.dto.CategoryDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryListResponseObject extends ResponseObject<Iterable<CategoryDTO>> {

    public CategoryListResponseObject(Iterable<CategoryDTO> data, String errorMessage, boolean success) {
        super(data, errorMessage, success);
    }

    public static CategoryListResponseObject ofData(Iterable<CategoryDTO> data) {
        return new CategoryListResponseObject(data, null, true);
    }

    public static CategoryListResponseObject ofErrorMessage(String errorMessage) {
        return new CategoryListResponseObject(null, errorMessage, false);
    }
}
