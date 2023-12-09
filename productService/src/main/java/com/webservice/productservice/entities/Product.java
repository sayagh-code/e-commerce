package com.webservice.productservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
}
