package com.example.library.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable @Data @NoArgsConstructor @AllArgsConstructor
public class ProductMarketId implements Serializable {
    private Long productId;
    private Long marketId;
}
