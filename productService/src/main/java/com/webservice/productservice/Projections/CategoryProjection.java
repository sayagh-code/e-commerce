package com.webservice.productservice.Projections;

import com.webservice.productservice.entities.Category;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "CategoryProjection", types = Category.class)
public interface CategoryProjection {
    String getId();
    String getNameCat();
}
