package com.example.test.repository;

import com.example.test.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();
    Category findById(int id);
    Category findByName(String name);
}
