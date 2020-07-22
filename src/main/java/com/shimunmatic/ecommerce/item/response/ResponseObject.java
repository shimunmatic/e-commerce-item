package com.shimunmatic.ecommerce.item.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseObject<T>{
    private T data;
    private String errorMessage;
    private boolean success;

    public static <T> ResponseObject<T> ofData(T data) {
        return new ResponseObject<>(data, null, true);
    }

    public static <T> ResponseObject<T> ofErrorMessage(String errorMessage) {
        return new ResponseObject<>(null, errorMessage, false);
    }

}