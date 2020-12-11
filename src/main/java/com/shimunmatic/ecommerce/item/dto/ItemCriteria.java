package com.shimunmatic.ecommerce.item.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
final public class ItemCriteria {
    private Long categoryId;
    private Integer page;
    private Integer pageSize;
    private String sortField;
    private Sort.Direction sortDirection;
}
