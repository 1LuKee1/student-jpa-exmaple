package com.example.studentjpaexmaple.infrastructure.client;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class CharacterHttpClientConfig {

    @Bean
    ErrorDecoder errorDecoder() {
        return new FeignClientResponseErrorHandler();
    }
}