package com.example.test.webflux;

import com.example.test.model.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CategoryReactRepository extends ReactiveCrudRepository<Category, Integer> {
    // returns reactive data, as streams (Flux, Mono) directly, it calls subscribe automatically
}
