package com.example.test.service;

import com.example.test.model.Item;
import com.example.test.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }
    public Item findItemById(int id) {
        return itemRepository.findById(id);
    }
    public List<Item> findItemByPriceIsLessThan(int price) {
        return itemRepository.findByPriceIsLessThan(price);
    }
    public List<Item> findItemByCategory(String category) {
        return itemRepository.findByCategory(category);
    }
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }
    public void deleteItemById(int id) {
        itemRepository.deleteById(id);
    }
}
