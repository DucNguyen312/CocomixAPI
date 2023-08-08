package com.example.library.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "product")
@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct")
    private Long id;
    private String name;
    private double price;
    private String quantity;
    private String note;
    private Timestamp create_time;
    private Timestamp update_time;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "idproduct", referencedColumnName = "idproduct"),
            inverseJoinColumns = @JoinColumn(name = "idcategory", referencedColumnName = "idcategory"))
    private Collection<Category> categories;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Collection<Product_Maket> productMakets;

}
