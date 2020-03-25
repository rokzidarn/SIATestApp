package com.example.test.repository;

import com.example.test.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findAll();
    Item findById(int id);
    List<Item> findByPriceIsLessThan(int price);
    List<Item> findByCategory(String category);
}