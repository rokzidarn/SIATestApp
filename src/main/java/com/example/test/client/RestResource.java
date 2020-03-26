package com.example.test.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootConfiguration
@ComponentScan
@Slf4j
public class RestResource {

    public static void main(String[] args) {
        SpringApplication.run(RestResource.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner runClient(RestClient restClient) {
        return args -> {
            log.info("Category OBJ:  " + restClient.getCategoryByIdOBJ(5));
            log.info("Category JSON:  " + restClient.getCategoryByIdJSON(5));
        };
    }

}