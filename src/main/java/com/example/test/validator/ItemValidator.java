package com.example.test.validator;

import com.example.test.model.Item;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    public boolean supports(Class c) {
        return Item.class.equals(c);
    }

    public void validate(Object obj, Errors e) {
        Item tmp = (Item) obj;

        if (tmp.getPrice() <= 5) {
            e.rejectValue("price", "price.low", "Price cannot be below $5!");
        } else if (tmp.getName().length() < 5) {
            e.rejectValue("name", "name.too.short", "Name too short!");
        } else if (tmp.getName().length() == 0) {
            e.rejectValue("name", "name.required", "Name is required!");
        } else if (tmp.getCategory() == null)  {
            e.rejectValue("category", "category.required", "Category is required!");
        }
    }
}