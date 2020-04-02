package com.example.test.service;

import com.example.test.model.Catalog;
import com.example.test.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("catalogService")
public class CatalogService {

    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public List<Catalog> findAllCatalogs() {
        return catalogRepository.findAll();
    }
    public Catalog findCatalogById(int id) {
        return catalogRepository.findById(id);
    }
    public Catalog saveCatalog(Catalog item) {
        return catalogRepository.save(item);
    }
}
