package com.example.library.Service;

import com.example.library.DTO.CategoryDTO;
import com.example.library.Model.Category;
import com.example.library.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategory();
    List<Product> findProductByid(Long id);
    Category saveCategory(CategoryDTO categoryDTO);
    Category updateCategory(Long id , CategoryDTO categoryDTO);
    String deleteCategory(Long id);
}
