package com.example.test.service;

import com.example.test.model.Category;
import com.example.test.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
    public Category findCategoryById(int id) {
        return categoryRepository.findById(id);
    }
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
}
