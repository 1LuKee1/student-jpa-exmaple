package com.example.studentjpaexmaple.infrastructure.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignClientResponseErrorHandler implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 500 -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while using http client");
            case 400 -> new ResponseStatusException(HttpStatus.NOT_FOUND);
            case 401 -> new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            default -> new Exception("Generic error");
        };
    }
}