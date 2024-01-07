package com.webservice.orderservice.models;

import lombok.Data;

@Data
public class Visitor {
    private String id;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String role;
}
