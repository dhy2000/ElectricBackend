package com.dbproject.electricbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class ElectricBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectricBackendApplication.class, args);
    }

}
