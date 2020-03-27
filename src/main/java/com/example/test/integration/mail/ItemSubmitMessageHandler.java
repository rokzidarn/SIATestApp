package com.example.test.integration.mail;

import java.util.Map;

import com.example.test.model.Item;
import com.example.test.service.ItemService;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.stereotype.Component;

@Component
public class ItemSubmitMessageHandler implements GenericHandler<Item> {

    private ItemService itemService;

    @Override
    public Object handle(Item item, Map<String, Object> headers) {
        itemService.saveItem(item);
        return null;
    }
}