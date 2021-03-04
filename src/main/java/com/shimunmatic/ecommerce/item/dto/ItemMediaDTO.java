package com.shimunmatic.ecommerce.item.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemMediaDTO {
    private Long id;
    private String name;
    private String path;
    private String type;
    private Long itemId;
}
