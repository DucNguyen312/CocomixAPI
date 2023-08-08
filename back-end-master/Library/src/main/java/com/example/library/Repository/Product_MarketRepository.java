package com.example.library.Repository;


import com.example.library.Model.Market;
import com.example.library.Model.ProductMarketId;
import com.example.library.Model.Product_Maket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface Product_MarketRepository extends JpaRepository<Product_Maket , Long> {
    Optional<Product_Maket> findByProductMarketId(ProductMarketId productMarketId);
    List<Product_Maket> findAllByMarket(Market market);
}
