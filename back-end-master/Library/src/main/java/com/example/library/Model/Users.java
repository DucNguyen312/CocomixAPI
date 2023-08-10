package com.example.library.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String fullName;

    private Date birthdate;

    private String phoneNumber;

    private String email;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "admins_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "users" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;

    @OneToMany(mappedBy = "users" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "users" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AccumlatePoints> accumlatePoints;

    @OneToMany(mappedBy = "users" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExchangePoints> exchangePoints;

    @OneToMany(mappedBy = "users" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Promotion> promotions;

}
