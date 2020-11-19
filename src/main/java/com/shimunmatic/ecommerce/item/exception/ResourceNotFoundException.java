package com.shimunmatic.ecommerce.item.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super("Resource was not found!");
    }
}
