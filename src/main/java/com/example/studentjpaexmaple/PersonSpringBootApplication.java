package com.example.studentjpaexmaple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PersonSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonSpringBootApplication.class, args);
    }
}
