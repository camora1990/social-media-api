package com.web.services.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition
public class ResfultWebServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResfultWebServicesApplication.class, args);
    }
}
