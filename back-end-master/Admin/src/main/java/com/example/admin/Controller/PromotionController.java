package com.example.admin.Controller;

import com.example.library.DTO.PromotionDTO;
import com.example.library.Model.Promotion;
import com.example.library.Service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @GetMapping("")
    public ResponseEntity<?> getPromotion(){
        return ResponseEntity.ok(promotionService.getListPromotion());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPromotionId(@PathVariable(name = "id") Long id){
        if (id == null)
            return ResponseEntity.ok("Not found id on path");
        Promotion promotion = promotionService.getPromotion(id);
        if (promotion == null)
            return ResponseEntity.ok("Not found promotion");
        return ResponseEntity.ok(promotion);
    }

    @PostMapping("")
    public ResponseEntity<?> createPromotion(@RequestBody PromotionDTO promotionDTO){
        if (promotionDTO == null)
            return ResponseEntity.badRequest().body("Not found value");
        return ResponseEntity.ok(promotionService.createPromotion(promotionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePromotion(@PathVariable(value = "id") Long id , @RequestBody PromotionDTO promotionDTO){
        if (id == null)
            return ResponseEntity.badRequest().body("Not found id on path");
        if (promotionDTO == null)
            return ResponseEntity.ok("Not found promotion");
        return ResponseEntity.ok(promotionService.updatePromotion(id , promotionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePromotion(@PathVariable(value = "id") Long id){
        if (id == null)
            return ResponseEntity.badRequest().body("Not found id on path");
        return ResponseEntity.ok(promotionService.deletePromotion(id));
    }

}
