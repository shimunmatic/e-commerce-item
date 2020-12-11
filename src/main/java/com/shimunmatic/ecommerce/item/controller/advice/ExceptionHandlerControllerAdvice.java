package com.shimunmatic.ecommerce.item.controller.advice;

import com.shimunmatic.ecommerce.item.exception.ResourceNotFoundException;
import com.shimunmatic.ecommerce.item.exception.RestException;
import com.shimunmatic.ecommerce.item.response.ResponseObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<ResponseObject<RestException>> handleException(ResourceNotFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return new ResponseEntity<>(ResponseObject.ofErrorMessage(new RestException(HttpStatus.NOT_FOUND, bodyOfResponse), bodyOfResponse), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ResponseObject<RestException>> handleException1(Exception ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return new ResponseEntity<>(ResponseObject.ofErrorMessage(new RestException(HttpStatus.INTERNAL_SERVER_ERROR, bodyOfResponse), bodyOfResponse), new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
