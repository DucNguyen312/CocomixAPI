package com.example.library.Service;

import com.example.library.DTO.ProductDTO;
import com.example.library.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    List<Product> findAllProduct();
    Optional<Product> findByIdProduct(Long id);
    Product saveProduct(ProductDTO p);
    Product updateProduct(Long id ,ProductDTO p);
    String deleteProduct(Long id);
    List<Product> searchProduct(String name);

}
