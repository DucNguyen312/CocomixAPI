package com.example.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data @AllArgsConstructor @NoArgsConstructor
public class ScreenDTO {
    private String name;
    private String images;
    private String description;
}
