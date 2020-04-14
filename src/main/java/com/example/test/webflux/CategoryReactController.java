package com.example.test.webflux;

/*
Spring MVC - blocking I/O, single thread per connection, non-effective scaling
    high latency when thread are moved in thread pool, meanwhile other requests are waiting, in other words
    one thread per request, a thread was blocked until the request processing had finished and the response was sent to the client
Spring WebFlux - non-blocking I/O, less threads when scaled,
    event looping allows to handle multiple requests per thread, event-based
    when a costly operation is needed, the event loop registers a callback for that operation to be performed
    in parallel, while it moves on to handle other events
WebSockets - also async communication, unlike HTTP it is bidirectional (binary or text)
*/

/*

import com.example.test.cassandra.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@CrossOrigin(origins="*")
public class CategoryReactController {
    private CategoryReactRepository categoryReactRepository;

    public CategoryReactController(CategoryReactRepository categoryReactRepository) {
        this.categoryReactRepository = categoryReactRepository;
    }

    @GetMapping(path="/react/category", produces="application/json")
    public Flux<Category> reactiveDataCategory() {
        return categoryReactRepository.findAll().take(12);
    }

    @GetMapping(path="/react/single", produces="application/json")
    public Mono<Category> reactiveDataSingle() {
        return categoryReactRepository.findAll().next();
    }
}

*/
