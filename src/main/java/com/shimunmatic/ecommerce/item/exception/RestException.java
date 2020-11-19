package com.shimunmatic.ecommerce.item.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestException {
    private final HttpStatus status;
    private final int statusCode;
    private final String reason;

    public RestException(HttpStatus status, String reason) {
        this.status = status;
        this.reason = reason;
        this.statusCode = status.value();
    }
}
