package com.example.library.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exchangePoints")
@Data @NoArgsConstructor @AllArgsConstructor
public class ExchangePoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idexchange;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idproduct", referencedColumnName = "idproduct")
    @JsonBackReference
    private Product products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_points", referencedColumnName = "id_points")
    private AccumlatePoints accumlatePoints;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonBackReference
    private Users users;

    private Long points_required;
    private Date exchange_date;
}
