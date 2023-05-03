package com.example.onlinestoreproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Instant creationDate = Instant.now();
    private Instant modificationDate = Instant.now();
    @ManyToMany
    @JoinTable(name="product_categories",
            joinColumns=  @JoinColumn(name="products_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="categories_id", referencedColumnName="id") )
    private Collection<Category> categories;
}
