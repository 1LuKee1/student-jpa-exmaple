package com.example.studentjpaexmaple.infrastructure.client;

import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CharacterHttpClientConfig {

    @Bean
    ErrorDecoder errorDecoder() {
        return new FeignClientResponseErrorHandler();
    }
}