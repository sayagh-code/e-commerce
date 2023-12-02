package com.webservice.orderservice;

import com.webservice.orderservice.entities.Bill;
import com.webservice.orderservice.entities.ProductItems;
import com.webservice.orderservice.feign.ProductRestClient;
import com.webservice.orderservice.feign.VisitorRestClient;
import com.webservice.orderservice.models.Product;
import com.webservice.orderservice.models.Visitor;
import com.webservice.orderservice.repositories.BillRepository;
import com.webservice.orderservice.repositories.ProductItemsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemsRepository productItemsRepository,
                            VisitorRestClient visitorRestClient,
                            ProductRestClient productRestClient){
        return args -> {
            Visitor visitor=visitorRestClient.getVisitorById(1L);
            Bill bill = billRepository.save(new Bill(null, new Date(),null,visitor.getId(),null));
            PagedModel<Product> productPagedModel=productRestClient.pageProducts();
            productPagedModel.forEach(p->{
                ProductItems productItems = new ProductItems();
                productItems.setPrice(p.getPrice());
                productItems.setQuantity(1 + new Random().nextInt(100));
                productItems.setBill(bill);
                productItems.setProductID(p.getId());
                productItemsRepository.save(productItems);
            });
        };
    }
}