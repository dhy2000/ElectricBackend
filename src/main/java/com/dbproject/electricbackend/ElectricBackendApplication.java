package com.dbproject.electricbackend;

import com.dbproject.electricbackend.config.FileConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@EnableConfigurationProperties({FileConfiguration.class})
public class ElectricBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElectricBackendApplication.class, args);
    }
}
