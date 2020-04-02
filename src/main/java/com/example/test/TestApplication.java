package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class TestApplication {  // https://github.com/habuma/spring-in-action-5-samples

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
