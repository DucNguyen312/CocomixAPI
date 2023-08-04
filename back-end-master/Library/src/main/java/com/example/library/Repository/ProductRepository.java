package com.example.library.Repository;

import com.example.library.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product , Long> {
    boolean existsByName(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByCategories_Id(Long id);

    Product findByName(String name);
}
