package com.shimunmatic.ecommerce.item.model;

public enum DiscountValueType {
    ABSOLUTE("ABSOLUTE"), PERCENT("PERCENT");

    private String name;

    DiscountValueType(String name) {
        this.name = name;
    }
}
