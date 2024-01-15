package com.webservice.productservice.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String image;

    public ProductDTO(String id, String name, String description, double price, int quantity, String image) {
        this.id = id;
        this.name=name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }
}
