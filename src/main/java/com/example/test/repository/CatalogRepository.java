package com.example.test.repository;

import com.example.test.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("catalogRepository")
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
    List<Catalog> findAll();
    Catalog findById(int id);
}
