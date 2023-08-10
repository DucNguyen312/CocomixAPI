package com.example.library.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "accumlatepoints")
@Data @NoArgsConstructor @AllArgsConstructor
public class AccumlatePoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_points")
    private Long idPoints;
    private Long Points;
    private Timestamp create_time;
    private Timestamp update_time;
    private Date expiration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users users;

    @OneToMany(mappedBy = "accumlatePoints")
    @JsonIgnore
    private Collection<ExchangePoints> exchangePoints;
}
