package com.shimunmatic.ecommerce.item.dto;

import com.shimunmatic.ecommerce.item.model.DiscountValueType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class DiscountDTO {
    private Long id;
    private String name;
    private String description;
    private Double value;
    private Instant dateValidFrom;
    private Instant dateValidTo;
    private Boolean active;
    private DiscountValueType discountValueType;
}
