package com.example.test.repository;

import com.example.test.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();
    Optional<Category> findById(int id);
    Category findByName(String name);
}
