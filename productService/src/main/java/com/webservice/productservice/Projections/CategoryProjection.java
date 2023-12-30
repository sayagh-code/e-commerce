package com.webservice.productservice.Projections;

import com.webservice.productservice.entities.Category;
import com.webservice.productservice.entities.Product;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "CategoryProjection", types = Category.class)
public interface CategoryProjection {
    String getId();
    String getNameCat();
}
