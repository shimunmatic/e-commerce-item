package com.shimunmatic.ecommerce.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private String thumbnail;
    private Long parentId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<CategoryDTO> subCategories;
}
