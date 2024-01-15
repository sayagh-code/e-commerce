package com.webservice.orderservice.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webservice.orderservice.entities.Bill;
import com.webservice.orderservice.entities.ProductItems;
import com.webservice.orderservice.feign.ProductRestClient;
import com.webservice.orderservice.feign.VisitorRestClient;
import com.webservice.orderservice.models.Product;
import com.webservice.orderservice.models.Visitor;
import com.webservice.orderservice.repositories.BillRepository;
import com.webservice.orderservice.repositories.ProductItemsRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class BillingRestController {
    private BillRepository billRepository;
    private ProductItemsRepository productItemsRepository;
    private VisitorRestClient visitorRestClient;
    private ProductRestClient productRestClient;

    public BillingRestController(BillRepository billRepository, ProductItemsRepository productItemsRepository, VisitorRestClient visitorRestClient, ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.productItemsRepository = productItemsRepository;
        this.visitorRestClient = visitorRestClient;
        this.productRestClient = productRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill=billRepository.findById(id).get();
        Visitor visitor = visitorRestClient.getVisitorById(bill.getVisitorID());
        bill.setVisitor(visitor);
        bill.getProductItems().forEach(p->{
            Product product=productRestClient.getProductById(p.getProductID());
            p.setProductName(product.getName());
        });
        return bill;
    }

    @PostMapping("/addBill")
    public Bill saveBill(@RequestParam String product, @RequestParam String customer) throws JsonProcessingException {
        Visitor visitor = visitorRestClient.getVisitorById(customer);
        List<Product> products = new ObjectMapper().readValue(product, new TypeReference<List<Product>>() {});
        Bill bill = billRepository.save(new Bill(null, new Date(),null,customer,visitor));
        products.forEach(p -> {
            ProductItems productItems = productItemsRepository.save(new ProductItems(null,p.getPrice(),p.getQuantity(), p.getId(), bill, p, p.getName()));
        });
        return bill;
    }

    @GetMapping("/getRecommendations/{id}")
    public ResponseEntity<String> recommend(@PathVariable String id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Bill> bill = billRepository.findAllByVisitorID(id);
        List<ProductItems> p = new ArrayList<>();
        bill.forEach(b -> {
            p.addAll(b.getProductItems());
        });
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String productsJson = objectMapper.writeValueAsString(p);
        System.out.println(productsJson);
        HttpEntity<String> requestEntity = new HttpEntity<>(productsJson, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://127.0.0.1:5000/recommendByUser", requestEntity, String.class);
        return responseEntity;
    }
}
