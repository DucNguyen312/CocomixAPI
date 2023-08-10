package com.example.library.Model;

import com.example.library.DTO.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "promotion")
@Data @NoArgsConstructor @AllArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpromotion")
    private Long id;
    private String name;
    private Integer decrease;
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "TINYINT")
    private Status status;

    @Column(name = "from_date")
    private Date from;

    @Column(name = "to_date")
    private Date to;
    private String applycondition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users users;

    @OneToMany(mappedBy = "promotions" , cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

}
