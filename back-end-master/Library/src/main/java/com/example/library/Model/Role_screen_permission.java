package com.example.library.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Role_screen_permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRole_screen_permission")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idscreen", referencedColumnName = "idscreen")
    private Screen screens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpermission", referencedColumnName = "idpermission")
    private Permission permissions;


}
