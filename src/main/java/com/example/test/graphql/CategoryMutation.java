package com.example.test.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.test.model.Category;
import com.example.test.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMutation implements GraphQLMutationResolver {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(final String name) {
        Category c = new Category();
        c.setName(name);
        return this.categoryRepository.save(c);
    }

    /*
    mutation {
        addCategory(name: "Undies")
        {
            id
        }
    }
     */
}