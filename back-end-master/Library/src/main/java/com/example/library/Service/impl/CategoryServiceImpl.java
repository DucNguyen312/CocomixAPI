package com.example.library.Service.impl;

import com.example.library.DTO.CategoryDTO;
import com.example.library.Model.Category;
import com.example.library.Model.Product;
import com.example.library.Repository.CategoryRepository;
import com.example.library.Repository.ProductRepository;
import com.example.library.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Category saveCategory(CategoryDTO categoryDTO) {
        if(categoryDTO == null)
            return null;
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            Category category1 = category.get();
            category1.setName(categoryDTO.getName());
            return categoryRepository.save(category1);
        }
        return null;
    }

    @Override
    public String deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            categoryRepository.deleteById(id);
            return "Delete Category success";
        }
        return "Not Found ID Category";
    }
}
