package com.webservice.orderservice.models;

import lombok.Data;

@Data
public class Visitor {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String type;
}
