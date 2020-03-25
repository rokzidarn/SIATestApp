package com.example.test.seeder;

import com.example.test.model.Category;
import com.example.test.model.Item;
import com.example.test.model.Role;
import com.example.test.model.User;
import com.example.test.repository.CategoryRepository;
import com.example.test.repository.ItemRepository;
import com.example.test.repository.RoleRepository;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Component
public class DatabaseSeeder {

    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private RoleRepository roleRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseSeeder(UserRepository userRepository, ItemRepository itemRepository, CategoryRepository categoryRepository,
                          RoleRepository roleRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.roleRepository = roleRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
        seedItemsTable();
    }

    private void seedUsersTable() {
        Role role1 = new Role();
        role1.setRole("USER");
        roleRepository.save(role1);

        Role role2 = new Role();
        role2.setRole("ADMIN");
        roleRepository.save(role2);

        User user = new User();
        user.setEmail("rok.zidarn@gmail.com");
        user.setFirstName("Rok");
        user.setLastName("Zidarn");
        user.setPassword(new BCryptPasswordEncoder().encode("test123"));
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        user.setActive(1);
        userRepository.save(user);
    }

    private void seedItemsTable() {
        Category c1 = new Category();
        c1.setName("Shoes");
        categoryRepository.save(c1);

        Category c2 = new Category();
        c2.setName("Pants");
        categoryRepository.save(c2);

        Category c3 = new Category();
        c3.setName("Sunglasses");
        categoryRepository.save(c3);

        Category c4 = new Category();
        c4.setName("Shirts");
        categoryRepository.save(c4);

        Category c5 = new Category();
        c5.setName("Socks");
        categoryRepository.save(c5);

        Item i1 = new Item();
        i1.setName("Nike Foamposite X1");
        i1.setPrice(120);
        i1.setCreated(new Date());
        i1.setCategory(c1);
        itemRepository.save(i1);

        Item i2 = new Item();
        i2.setName("Levi's jeans");
        i2.setPrice(80);
        i2.setCreated(new Date());
        i2.setCategory(c2);
        itemRepository.save(i2);

        Item i3 = new Item();
        i3.setName("Rayban Aviators");
        i3.setPrice(90);
        i3.setCreated(new Date());
        i3.setCategory(c3);
        itemRepository.save(i3);

        Item i4 = new Item();
        i4.setName("Oakley Supersport");
        i4.setPrice(70);
        i4.setCreated(new Date());
        i4.setCategory(c3);
        itemRepository.save(i4);

        Item i5 = new Item();
        i5.setName("Adidas Gaucho");
        i5.setPrice(30);
        i5.setCreated(new Date());
        i5.setCategory(c1);
        itemRepository.save(i5);
    }
}