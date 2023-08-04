package com.example.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data @NoArgsConstructor @AllArgsConstructor
public class User_OrderDTO {
    private Long iduser;
    private String name;
    private Date ngaydat;
    private String address;
    private Long idorder;
}
