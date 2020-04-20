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

    // @Configuration, @Bean, @Component, @Service, @Autowired, @Resource, @Inject, @Value, @Scope, @Slf4j
    // @Controller, @RequestMapping, @PathVariable, @RequestParam, @Valid, @CrossOrigin, @RequestBody, @ResponseStatus
    // @Transactional, @Repository, @Data, @Document, @Table, @UserDefinedType, @Builder, @Entity
    // @MessagingGateway,  @Transformer,  @ServiceActivator, @EventListener, @Test, @Mock, @Before

    // TODO: implement Order functionality
    // TODO: set up admin access, disable user access
    // TODO: controller/ : custom Item validator, ManyToMany relationship Catalog<->Item
    // TODO: resources/ : set views for other endpoints (Thymeleaf or React)
}
