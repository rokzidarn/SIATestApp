package com.example.test.webflux;

import com.example.test.model.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins="*")
public class CategoryReactController {
    private CategoryReactRepository categoryReactRepository;

    public CategoryReactController(CategoryReactRepository categoryReactRepository) {
        this.categoryReactRepository = categoryReactRepository;
    }

    /*
    Spring MVC - blocking I/O, single thread per connection, non-effective scaling
        high latency when thread are moved in thread pool, meanwhile other requests are waiting
    Spring WebFlux - non-blocking I/O, less threads when scaled,
        event looping allows to handle multiple requests per thread, event-based
        when a costly operation is needed, the event loop registers a callback for that operation to be performed
        in parallel, while it moves on to handle other events

    */

    @GetMapping(path="/react/category", produces="application/json")
    public Flux<Category> reactiveDataCategory() {
        return categoryReactRepository.findAll().take(12);
    }

    @GetMapping(path="/react/single", produces="application/json")
    public Mono<Category> reactiveDataSingle() {
        return categoryReactRepository.findById(6);
    }
}
