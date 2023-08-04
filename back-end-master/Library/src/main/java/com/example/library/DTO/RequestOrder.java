package com.example.library.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class RequestOrder {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date ngaydat;
    private String name;
    private String address;
    private String phonenumber;
    private String note;
    private String totalproducts;
    private Integer quantity;
    private double price;
    private double totalprice;
}
