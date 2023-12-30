package com.webservice.productservice;

import com.webservice.productservice.entities.Category;
import com.webservice.productservice.entities.Product;
import com.webservice.productservice.repositories.CategoryRepository;
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
    CommandLineRunner start(ProductRepository productRepository, CategoryRepository categoryRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Product.class);
        restConfiguration.exposeIdsFor(Category.class);
        return args -> {
            Category AllCat = categoryRepository.save(new Category(UUID.randomUUID().toString(),"All", null));
            Category categoryElec = categoryRepository.save(new Category(UUID.randomUUID().toString(),"electronics", null));
            Category categoryDrink = categoryRepository.save(new Category(UUID.randomUUID().toString(),"drinks", null));
            Category categoryCloth = categoryRepository.save(new Category(UUID.randomUUID().toString(),"cloths", null));
            Category categoryMake = categoryRepository.save(new Category(UUID.randomUUID().toString(),"make up", null));
        };
    }

}