package com.webservice.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webservice.productservice.dtos.ProductDTO;
import com.webservice.productservice.entities.Category;
import com.webservice.productservice.entities.Product;
import com.webservice.productservice.repositories.CategoryRepository;
import com.webservice.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, CategoryRepository categoryRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Product.class);
        restConfiguration.exposeIdsFor(Category.class);
        return args -> {
            /*Category AllCat = categoryRepository.save(new Category(UUID.randomUUID().toString(),"All", null));
            Category categoryElec = categoryRepository.save(new Category(UUID.randomUUID().toString(),"electronics", null));
            Category categoryDrink = categoryRepository.save(new Category(UUID.randomUUID().toString(),"drinks", null));
            Category categoryCloth = categoryRepository.save(new Category(UUID.randomUUID().toString(),"cloths", null));
            Category categoryMake = categoryRepository.save(new Category(UUID.randomUUID().toString(),"make up", null));*/
            ObjectMapper objectMapper = new ObjectMapper();
            List<ProductDTO> productList = productRepository.findAll()
                    .stream()
                    .map(product -> new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getImage()))
                    .collect(Collectors.toList());
            String productJson = objectMapper.writeValueAsString(productList);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestEntity = new HttpEntity<>(productJson, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://127.0.0.1:5000/initialise", requestEntity, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                System.out.println("Request successful!"+ responseEntity.getBody());
            } else {
                System.out.println("Request failed with status code: " + responseEntity.getStatusCodeValue());
            }
        };
    }
}