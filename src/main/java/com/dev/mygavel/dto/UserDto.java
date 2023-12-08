package com.dev.mygavel.dto;

import java.util.Date;
import java.util.List;

import com.dev.mygavel.entities.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String telephone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    //private List<Role> roles;
}
