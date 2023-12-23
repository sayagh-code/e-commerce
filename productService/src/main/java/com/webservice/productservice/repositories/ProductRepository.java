package com.webservice.productservice.repositories;

import com.webservice.productservice.Projections.ProductProjection;
import com.webservice.productservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = ProductProjection.class)
public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findByCategory_NameCat(String nameCat);
}
