package com.shimunmatic.ecommerce.item.model;

import lombok.Getter;

public enum DiscountValueType {
    ABSOLUTE("ABSOLUTE"), PERCENT("PERCENT");

    @Getter
    private final String name;

    DiscountValueType(String name) {
        this.name = name;
    }
}
