package com.example.test.controller;

import com.example.test.model.Category;
import com.example.test.repository.CategoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/categories", produces="application/json")
@CrossOrigin(origins="*")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/recent")
    public Iterable<Category> recentCategories() {
        PageRequest page = PageRequest.of(0, 2, Sort.by("created").descending());
        return categoryRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> categoryById(@PathVariable("id") int id) {
        Optional<Category> optCategory = categoryRepository.findById(id);
        if (optCategory.isPresent()) {
            return new ResponseEntity<>(optCategory.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    /* REQUEST EXAMPLE
    {
	    "name": "Hats"
    }
    */

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("id") int id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}
    }

    // with Spring Data REST API endpoints exposed, possible paging, size and sorting
    // http://localhost:8080/api/categories?size=2&page=1
}
