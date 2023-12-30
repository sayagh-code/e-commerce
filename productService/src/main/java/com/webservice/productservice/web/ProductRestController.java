package com.webservice.productservice.web;

import com.webservice.productservice.entities.Product;
import com.webservice.productservice.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductRestController {
    private ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/restProducts")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return ResponseEntity.ok(this.productRepository.save(product));
    }
}
