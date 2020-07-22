package com.shimunmatic.ecommerce.item.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private Long parentId;
    private List<CategoryDTO> subCategories;
}
