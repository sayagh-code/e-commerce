package com.webservice.productservice.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webservice.productservice.dtos.ProductDTO;
import com.webservice.productservice.entities.Product;
import com.webservice.productservice.repositories.ProductRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductRestController {
    private ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/restProducts")
    public ResponseEntity<ProductDTO> save(@RequestBody Product product) {
        Product p=this.productRepository.save(product);
        ProductDTO productDTO = new ProductDTO(p.getId(), p.getName(), p.getDescription(), product.getPrice(), p.getQuantity(), p.getImage());
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping("/getRecommendations")
    public ResponseEntity<String> getRecommendations(@RequestBody String productName) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String productJson = objectMapper.writeValueAsString(productName);
        HttpEntity<String> requestEntity = new HttpEntity<>(productJson, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://127.0.0.1:5000/recommend", requestEntity, String.class);
        System.out.println(responseEntity.getBody());
        return responseEntity;
    }
}