package com.example.user.Controller;

import com.example.library.Model.Category;
import com.example.library.Model.Product;
import com.example.library.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> findAllCateGory(){
        return ResponseEntity.ok(categoryService.findAllCategory());
    }
    @GetMapping("/{categoryId}/product")
    public ResponseEntity<List<Product>> listProduct(@PathVariable(value = "categoryId") Long id){
        if(id == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(categoryService.findProductByid(id));
    }
}
