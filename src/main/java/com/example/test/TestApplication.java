package com.example.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class TestApplication {
    private static final Logger logger = LoggerFactory.getLogger(TestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
        logger.info("Hello SIATestApp!");
    }

    // @Configuration, @Bean, @Component, @Repository, @Service, @Controller, @Autowired, @Resource, @Inject, @Scope
    // @RequestMapping, @PathVariable, @RequestParam, @Valid, @CrossOrigin, @RequestBody, @ResponseStatus
    // 
}
