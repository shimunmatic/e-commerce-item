package com.shimunmatic.ecommerce.item.util;

import com.shimunmatic.ecommerce.item.dto.UploadResult;
import com.shimunmatic.ecommerce.item.response.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
public class CDNUploadUtil {
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public static ResponseObject<UploadResult> uploadMediaToCdn(String url, MultipartFile file) throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(file.getBytes(), file.getOriginalFilename()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<ResponseObject<UploadResult>> response = REST_TEMPLATE.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<>() {
        });
        if (response.getStatusCode() != HttpStatus.OK) {
            log.error("Error while uploading media to CDN");
        }
        return response.getBody();
    }
}
