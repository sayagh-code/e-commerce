package com.webservice.productservice.repositories;

import com.webservice.productservice.Projections.CategoryProjection;
import com.webservice.productservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = CategoryProjection.class)
public interface CategoryRepository  extends JpaRepository<Category,String> {
}
