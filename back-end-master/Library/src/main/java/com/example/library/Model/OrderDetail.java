package com.example.library.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "orderDetail")
@Data @AllArgsConstructor @NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorderDetail")
    private Long id;
    private Integer quantity;
    private double price;
    private double totalprice;
    private Timestamp create_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idproduct", referencedColumnName = "idproduct")
    private Product products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idorder", referencedColumnName = "idorder")
    @JsonBackReference
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonBackReference
    private Users users;
}
