package com.dev.mygavel.dto;

import com.dev.mygavel.entities.Role;
import com.dev.mygavel.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OfficeDto {

    private Long id;
    private String nom;
    private String email;
    private String telephone;
    private Date dateCreation;
    private List<User> users;
    private String logo;
    private String siteWeb;
}
