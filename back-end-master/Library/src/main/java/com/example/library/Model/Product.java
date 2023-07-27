package com.example.library.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.*;
import java.util.Collection;

@Entity
@Table(name = "product")
@Data @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idproduct")
    private Long id;
    private String name;
    private double price;
    private String quantity;
    private String note;
    private Timestamp create_time;
    private Timestamp update_time;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "idproduct", referencedColumnName = "idproduct"),
            inverseJoinColumns = @JoinColumn(name = "idcategory", referencedColumnName = "idcategory"))
    private Collection<Category> categories;
}
