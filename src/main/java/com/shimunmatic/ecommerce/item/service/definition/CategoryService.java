package com.shimunmatic.ecommerce.item.service.definition;

import com.shimunmatic.ecommerce.item.dto.CategoryDTO;
import com.shimunmatic.ecommerce.item.model.Category;

import java.util.List;

public interface CategoryService extends CRUDService<Category, Long> {

    List<CategoryDTO> getAllDto();

    CategoryDTO saveDTO(CategoryDTO dto);
}
