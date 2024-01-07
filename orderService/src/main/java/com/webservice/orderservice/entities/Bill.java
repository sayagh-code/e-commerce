package com.webservice.orderservice.entities;

import com.webservice.orderservice.models.Visitor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date orderDate;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItems> productItems;
    private String visitorID;
    @Transient
    private Visitor visitor;
}
