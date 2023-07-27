package com.example.library.Model;

import javax.persistence.*;

@Entity
@Table(name = "product_category")
public class Product_Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
