package com.shimunmatic.ecommerce.item.converter;

import com.shimunmatic.ecommerce.item.dto.CategoryDTO;
import com.shimunmatic.ecommerce.item.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<Category, CategoryDTO> {

    @Override
    public CategoryDTO toDto(Category category) {
        if (category == null) { return null; }
        return CategoryDTO.builder().id(category.getId()).name(category.getName()).description(category.getDescription()).parentId(category.getParentId())
                          .subCategories(this.toDto(category.getSubCategories())).build();
    }

    @Override
    public Category toModel(CategoryDTO categoryDTO) {
        if (categoryDTO == null) { return null; }
        Category category = new Category(categoryDTO.getName(), categoryDTO.getDescription(), categoryDTO.getParentId(), null, toModel(categoryDTO.getSubCategories()));
        category.setId(categoryDTO.getId());

        return category;
    }
}
