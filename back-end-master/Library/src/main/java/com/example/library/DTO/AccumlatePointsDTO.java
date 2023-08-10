package com.example.library.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccumlatePointsDTO {
    private Long Points;
    private String create_time;
    private String update_time;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date expiration;
}
