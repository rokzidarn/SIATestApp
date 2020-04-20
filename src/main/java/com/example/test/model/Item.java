package com.example.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {  // Hibernate is JPA implementation of ORM

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private int id;

    @NotNull(message = "Please provide item name!")
    @Size(min=3, message="Name must be at least 3 characters long!")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Please provide item price!")
    @Min(value=5, message="Price must be at least $5!")
    @Column(name = "price")
    private int price;

    private Date created;
    @PrePersist
    void createdAt() {
        this.created = new Date();
    }

    @ManyToOne(fetch = FetchType.LAZY)  // all items in this category will be fetched when needed, not right away (EAGER)
    @JoinColumn(name = "category_id", nullable = false)  // cannot create an item without a category
    private Category category;
}
