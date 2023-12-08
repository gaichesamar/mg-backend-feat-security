package com.dev.mygavel.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "offices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Office implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = true)
    private String nom;

    @Column(name = "email", nullable = false)
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_creation", nullable = true)
    private Date dateCreation;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_Supp", nullable = true)
    private Date dateSupp;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OFFICE_USERS", joinColumns = {@JoinColumn(name = "OFFICE_ID")}, inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private List<User> users;

    @Column(name = "logo", nullable = true)
    private String logo;
    @Column(name = "site_web", nullable = true)
    private String siteWeb;
}
