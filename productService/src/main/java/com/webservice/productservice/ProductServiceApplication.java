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
            productRepository.save(new Product(UUID.randomUUID().toString(),"PC","",10000,5, categoryElec,"nfcCard.png"));
            productRepository.save(new Product(UUID.randomUUID().toString(),"phone","",8000,10, categoryElec,"nfcCard.png"));
            productRepository.save(new Product(UUID.randomUUID().toString(),"shirt","",500,1000, categoryCloth, "nfcCard.png"));
            productRepository.save(new Product(UUID.randomUUID().toString(),"lipstick","",100,800, categoryMake, "nfcCard.png"));
            productRepository.save(new Product(UUID.randomUUID().toString(),"socks","",20,8000, categoryCloth, "nfcCard.png"));
            productRepository.save(new Product(UUID.randomUUID().toString(),"hoodie","",200,100, categoryCloth, "nfcCard.png"));
            productRepository.save(new Product(UUID.randomUUID().toString(),"ice tea","",100,800, categoryDrink, "nfcCard.png"));
            productRepository.save(new Product(UUID.randomUUID().toString(),"Tops","",5,8000, categoryDrink, "nfcCard.png"));
            productRepository.save(new Product(UUID.randomUUID().toString(),"Oualmes","",2,300, categoryDrink, "nfcCard.png"));
        };
    }

}