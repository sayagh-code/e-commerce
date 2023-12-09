package com.webservice.productservice;

import com.webservice.productservice.entities.Product;
import com.webservice.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.UUID;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Product.class);
        return args -> {
            productRepository.save(new Product(UUID.randomUUID().toString(),"PC","",10000,5));
            productRepository.save(new Product(UUID.randomUUID().toString(),"phone","",8000,10));
            productRepository.save(new Product(UUID.randomUUID().toString(),"shirt","",500,1000));
            productRepository.save(new Product(UUID.randomUUID().toString(),"lipstick","",100,800));
            productRepository.save(new Product(UUID.randomUUID().toString(),"socks","",20,8000));
            productRepository.save(new Product(UUID.randomUUID().toString(),"hoodie","",200,100));
            productRepository.save(new Product(UUID.randomUUID().toString(),"lipstick","",100,800));
        };
    }

}
