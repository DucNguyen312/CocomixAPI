package com.example.admin.Controller;

import com.example.library.DTO.ProductDTO;
import com.example.library.Model.Product;
import com.example.library.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Product>> findAll(@RequestParam(value = "query" , required = false) String keyword){
        if(keyword != null)
            return ResponseEntity.ok(productService.searchProduct(keyword));
        return ResponseEntity.ok(productService.findAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findProduct(@PathVariable(value = "id") Long id){
        if(id == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(productService.findByIdProduct(id));
    }

    @PostMapping("")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO){
        if(productDTO == null)
            return ResponseEntity.badRequest().body("Add Product Fail");
        productService.saveProduct(productDTO);
        return ResponseEntity.ok("Add Product Success");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable(value = "id") Long id , @RequestBody ProductDTO productDTO){
        if(id == null)
            ResponseEntity.ok("Not found id");
        if(productDTO == null)
            ResponseEntity.badRequest().body("Not found value");
        productService.updateProduct(id , productDTO);
        return ResponseEntity.ok("Update Product Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") Long id){
        if (id == null)
            return ResponseEntity.badRequest().body("Not found id");
        productService.deleteProduct(id);
        return ResponseEntity.badRequest().body("Delete Product success");
    }

}
