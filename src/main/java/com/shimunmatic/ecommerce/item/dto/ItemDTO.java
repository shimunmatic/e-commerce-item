package com.shimunmatic.ecommerce.item.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ItemDTO {
    private Long id;
    private String name;
    private String description;
    private Double basePrice;
    private Long categoryId;
    private List<ItemVariantDTO> variants;
}
