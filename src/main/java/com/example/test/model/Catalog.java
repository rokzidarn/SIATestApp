package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "catalog")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "catalog_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "num_pages")
    private int numPages;

    private Date created;
    @PrePersist
    void createdAt() {
        this.created = new Date();
    }
}