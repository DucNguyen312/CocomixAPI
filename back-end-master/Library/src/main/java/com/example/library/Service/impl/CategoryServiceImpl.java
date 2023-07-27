package com.example.library.Service.impl;

import com.example.library.Model.Category;
import com.example.library.Model.Product;
import com.example.library.Repository.CategoryRepository;
import com.example.library.Repository.ProductRepository;
import com.example.library.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }
    @Override
    public List<Product> findProductByid(Long id) {
        return productRepository.findByCategories_Id(id);
    }
}
