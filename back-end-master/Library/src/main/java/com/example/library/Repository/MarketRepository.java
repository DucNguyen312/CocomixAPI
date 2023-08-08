package com.example.library.Repository;


import com.example.library.Model.Market;
import com.example.library.Model.Product_Maket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market , Long> {
    boolean existsByName(String name);

}
