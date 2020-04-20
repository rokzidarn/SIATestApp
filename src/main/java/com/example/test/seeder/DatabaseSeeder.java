package com.example.test.seeder;

import com.example.test.model.*;
import com.example.test.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseSeeder {

    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private RoleRepository roleRepository;
    private CatalogRepository catalogRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseSeeder(UserRepository userRepository, ItemRepository itemRepository, CategoryRepository categoryRepository,
                          RoleRepository roleRepository,  CatalogRepository catalogRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.roleRepository = roleRepository;
        this.catalogRepository = catalogRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
        seedItemsTable();
        seedCatalogsTable();
    }

    private void seedUsersTable() {
        Role role1 = new Role();
        role1.setRole("USER");
        roleRepository.save(role1);

        Role role2 = new Role();
        role2.setRole("ADMIN");
        roleRepository.save(role2);

        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setPassword(new BCryptPasswordEncoder().encode("admin000"));
        Role adminRole = roleRepository.findByRole("ADMIN");
        admin.setRoles(new HashSet<Role>(Arrays.asList(adminRole)));
        admin.setActive(1);
        userRepository.save(admin);

        User user1 = new User();
        user1.setEmail("rok.zidarn@gmail.com");
        user1.setFirstName("Rok");
        user1.setLastName("Zidarn");
        user1.setPassword(new BCryptPasswordEncoder().encode("test123"));
        Role userRole1 = roleRepository.findByRole("USER");
        user1.setRoles(new HashSet<Role>(Arrays.asList(userRole1)));
        user1.setActive(1);
        userRepository.save(user1);

        User user2 = new User();
        user2.setEmail("janez.novak@gmail.com");
        user2.setFirstName("Janez");
        user2.setLastName("Novak");
        user2.setPassword(new BCryptPasswordEncoder().encode("geslo456"));
        Role userRole2 = roleRepository.findByRole("USER");
        user2.setRoles(new HashSet<Role>(Arrays.asList(userRole2)));
        user2.setActive(1);
        userRepository.save(user2);
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

    private void seedCatalogsTable() {
        Catalog k1 = new Catalog();
        k1.setName("Spring/Summer 2020 Collection");
        k1.setNumPages(65);
        k1.setCreated(new Date());
        catalogRepository.save(k1);
    }
}