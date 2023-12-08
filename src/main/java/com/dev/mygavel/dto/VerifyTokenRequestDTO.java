package com.dev.mygavel.dto;

import lombok.Data;

@Data
public class VerifyTokenRequestDTO {


    private String username;
    private Integer otp;
    private Boolean rememberMe;

}