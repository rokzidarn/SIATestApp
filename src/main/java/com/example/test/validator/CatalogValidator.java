package com.example.test.validator;

import com.example.test.model.Catalog;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CatalogValidator implements Validator {

    public boolean supports(Class c) {
        return Catalog.class.equals(c);
    }

    public void validate(Object obj, Errors e) {
        Catalog c = (Catalog) obj;

        if (c.getNumPages() < 0) {
            e.rejectValue("numPages", "number.of.pages.not.negative", "Number of pages cannot be negative!");
        } else if (c.getName().length() > 10) {
            e.rejectValue("name", "name.too.long", "Name too long!");
        } else if (c.getName().length() == 0) {
            e.rejectValue("name", "name.too.short", "Name too short!");
        }
    }
}