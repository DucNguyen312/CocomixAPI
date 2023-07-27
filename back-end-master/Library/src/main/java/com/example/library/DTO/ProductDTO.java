package com.example.library.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.sql.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @Size(min = 3 , max = 45,message = "Invalid product name!(3-45 characters)")
    private String name;

    private double price;

    private String quantity;

    private String note;

    private String create_time;

    private String update_time;
}
