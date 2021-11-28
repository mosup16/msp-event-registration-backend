package com.example.mspeventregistration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MspEventRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MspEventRegistrationApplication.class, args);
    }

}
