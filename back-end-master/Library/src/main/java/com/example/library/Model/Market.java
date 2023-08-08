package com.example.library.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "market")
@Data @NoArgsConstructor @AllArgsConstructor
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmarket")
    private Long id;
    private String name;
    private Double price_increase;

    @OneToMany(mappedBy = "market")
    @JsonIgnore
    private Collection<Product_Maket> productMakets;
}
