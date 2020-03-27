package com.example.test.graphql;

        import com.coxautodev.graphql.tools.GraphQLQueryResolver;
        import com.example.test.model.Category;
        import com.example.test.service.CategoryService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;

        import java.util.List;
        import java.util.Optional;

@Component
public class CategoryQuery implements GraphQLQueryResolver{
    @Autowired
    private CategoryService categoryService;

    public List<Category> getAllCategories(final int count) {
        return this.categoryService.findAllCategories();
    }

    public Optional<Category> getCategory(final int id) {
        return this.categoryService.findCategoryById(id);
    }

    /*
    query {
        getAllCategories(count: 3)
        {
            category_id,
            name
        }
    }
     */
}