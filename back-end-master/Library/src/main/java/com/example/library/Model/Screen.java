package com.example.library.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idscreen")
    private Long id;
    private String name;

    @Lob
    private byte[] images;
    private String description;
}
