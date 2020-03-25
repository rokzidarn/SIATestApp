package com.example.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private int id;

    @NotEmpty(message = "Please provide category name!")
    @Column(name = "name")
    private String name;

    private Date created;
    @PrePersist
    void createdAt() {
        this.created = new Date();
    }

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Item> items;
}