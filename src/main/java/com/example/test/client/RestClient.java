package com.example.test.client;

import com.example.test.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestClient {

    private RestTemplate rest;  // only works with JSON endpoints, not with objects

    public RestClient(RestTemplate rest) {
        this.rest = rest;
    }

    public Category getCategoryByIdOBJ(int id) {
        return rest.getForObject("http://localhost:8080/categories/{id}", Category.class, id);
    }

    public Category getCategoryByIdJSON(int id) {
        ResponseEntity<Category> responseEntity = rest.getForEntity("http://localhost:8080/categories/{id}", Category.class, id);
        return responseEntity.getBody();
    }
}
