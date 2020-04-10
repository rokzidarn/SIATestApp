package com.example.test.repository;

import com.example.test.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Integer> {  // PagingAndSortingRepository<Item, Integer>
    List<Item> findAll();
    Item findById(int id);  // can also be optional
    List<Item> findByPriceIsLessThan(int price);
    List<Item> findByCategory(String category);

    // @Query("SELECT i FROM items WHERE i.name LIKE %1")  // custom SQL query
    // List<Item> findBySimilarName(String something)
}