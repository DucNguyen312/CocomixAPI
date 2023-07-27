package com.example.admin.Controller;

import com.example.library.DTO.CategoryDTO;
import com.example.library.Model.Category;
import com.example.library.Model.Product;
import com.example.library.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("")
    public ResponseEntity<String> saveCategory(@RequestBody CategoryDTO categoryDTO){
        if(categoryDTO == null)
            return ResponseEntity.badRequest().body("Not found value");
        categoryService.saveCategory(categoryDTO);
        return ResponseEntity.ok("Add category success");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable(value = "id") Long id , @RequestBody CategoryDTO categoryDTO){
        if(id == null)
            return ResponseEntity.badRequest().body("Not found id");
        if (categoryDTO == null)
            return ResponseEntity.badRequest().body("Not found value");
        categoryService.updateCategory(id , categoryDTO);
        return ResponseEntity.ok("Update category success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(value = "id") Long id){
        if(id == null)
            return ResponseEntity.badRequest().body("Not found id");
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Delete category success");
    }
}
