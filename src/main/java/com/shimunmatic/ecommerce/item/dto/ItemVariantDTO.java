package com.shimunmatic.ecommerce.item.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemVariantDTO {
    private Long id;
    private String name;
    private String description;
    private String thumbnail;
    private Double basePrice;
    private Long itemId;
}
