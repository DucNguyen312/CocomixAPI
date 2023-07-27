package com.example.library.Service.impl;

import com.example.library.DTO.ProductDTO;
import com.example.library.Model.Product;
import com.example.library.Repository.CategoryRepository;
import com.example.library.Repository.ProductRepository;
import com.example.library.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;
import java.util.Optional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findByIdProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(ProductDTO p) {
        Product product = new Product();
        product.setName(p.getName());
        product.setPrice(p.getPrice());
        product.setQuantity(p.getQuantity());
        product.setNote(p.getNote());
        product.setCreate_time(convertToDate(p.getCreate_time()));
        product.setUpdate_time(convertToDate(p.getUpdate_time()));
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO p) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            Product pr = product.get();
            pr.setName(p.getName());
            pr.setPrice(p.getPrice());
            pr.setQuantity(p.getQuantity());
            pr.setNote(p.getNote());
            pr.setCreate_time(convertToDate(p.getCreate_time()));
            pr.setUpdate_time(convertToDate(p.getUpdate_time()));
            return productRepository.save(pr);
        }
        return null;
    }

    @Override
    public String deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return "Delete product success";
        }
        return "Not found id product";
    }

    @Override
    public List<Product> searchProduct(String name) {
        return productRepository.findByNameContaining(name);
    }
    public Timestamp convertToDate(String date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dateObject = dateFormat.parse(date);
            Timestamp timestamp = new Timestamp(dateObject.getTime());
            return timestamp;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
