package com.example.library.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Product_Maket {
    @EmbeddedId
    private ProductMarketId productMarketId;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "idproduct")
    private Product product;

    @ManyToOne
    @MapsId("marketId")
    @JoinColumn(name = "idmarket")
    private Market market;

    private Double priceOld;
    private Double priceIncrease;
}
