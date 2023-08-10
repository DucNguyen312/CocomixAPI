package com.example.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ExchangePointsDTO {
    private Long points_required;
    private String exchange_date;
}
