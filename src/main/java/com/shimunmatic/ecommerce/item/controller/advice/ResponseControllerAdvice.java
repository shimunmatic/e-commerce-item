package com.shimunmatic.ecommerce.item.controller.advice;

import com.shimunmatic.ecommerce.item.exception.RestException;
import com.shimunmatic.ecommerce.item.response.ResponseObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@SuppressWarnings("NullableProblems")
@RestControllerAdvice
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (body instanceof ResponseEntity<?> re) {
            serverHttpResponse.setStatusCode(re.getStatusCode());
            if (re.getBody() instanceof RestException r) {return ResponseObject.ofErrorMessage(r, r.getReason()); }
            return re;
        } else if (body instanceof RestException r) { return ResponseObject.ofErrorMessage(r, r.getReason()); }

        return ResponseObject.ofData(body);
    }
}
