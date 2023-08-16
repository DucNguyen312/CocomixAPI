package com.example.user.Controller;

import com.example.library.DTO.MarketDTO;
import com.example.library.DTO.Product_MarketDTO;
import com.example.library.Model.Market;
import com.example.library.Model.Product;
import com.example.library.Service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {
    @Autowired
    private MarketService marketService;

    @GetMapping("")
    public ResponseEntity<List<Market>> getAllMarket(){
        return ResponseEntity.ok(marketService.getAllMarket());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Market> getMartket(@PathVariable(value = "id") Long id){
        if (id == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(marketService.getMarket(id));
    }

    @GetMapping("/{marketId}/product")
    public ResponseEntity<List<Product>> getListProduct_Market(@PathVariable(value = "marketId") Long marketId){
        if (marketId == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(marketService.getListProduct_Market(marketId));
    }


    @PostMapping("")
    public ResponseEntity<String> createMarket(@RequestBody MarketDTO marketDTO){
        if (marketDTO == null)
            return ResponseEntity.ok("Not found value");
        return ResponseEntity.ok(marketService.createMarket(marketDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMarket(@PathVariable(value = "id") Long id, @RequestBody MarketDTO marketDTO){
        if(id == null)
            return ResponseEntity.badRequest().body("Not found id");
        if (marketDTO == null)
            return ResponseEntity.badRequest().body("Not found value");
        return ResponseEntity.ok(marketService.updateMarket(id , marketDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMarket(@PathVariable(value = "id")Long id){
        if(id == null)
            return ResponseEntity.badRequest().body("Not found id");
        return ResponseEntity.ok(marketService.deleteMarket(id));
    }

    @PutMapping("/{marketId}/product/{productId}")
    public ResponseEntity<String> updateMarket_Product(@PathVariable(value = "marketId") Long marketId , @PathVariable(value = "productId") Long productId , @RequestBody Product_MarketDTO productMarketDTO){
        if (marketId == null || productId == null)
            return ResponseEntity.badRequest().body("Not found id");
        if (productMarketDTO == null)
            return ResponseEntity.badRequest().body("Not found value");
        return ResponseEntity.ok(marketService.updateProductMarket(marketId , productId , productMarketDTO));
    }

    @DeleteMapping("/{marketId}/product/{productId}")
    public ResponseEntity<String> deleteMarket_Product(@PathVariable(value = "marketId") Long marketId , @PathVariable(value = "productId") Long productId){
        if (marketId == null || productId == null)
            return ResponseEntity.badRequest().body("Not found id");
        return ResponseEntity.ok(marketService.deleteProductMarket(marketId , productId));
    }


}
