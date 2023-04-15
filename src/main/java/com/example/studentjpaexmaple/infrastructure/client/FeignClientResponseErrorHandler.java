package com.example.studentjpaexmaple.infrastructure.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class FeignClientResponseErrorHandler implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == SERVER_ERROR.value()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while using http client");
        } else if (response.status()  == CLIENT_ERROR.value()) {
            if (response.status()  == HttpStatus.NOT_FOUND.value()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else if (response.status()  == HttpStatus.UNAUTHORIZED.value()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}