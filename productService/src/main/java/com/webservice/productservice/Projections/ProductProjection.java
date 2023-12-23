package com.webservice.productservice.Projections;

import com.webservice.productservice.entities.Category;
import com.webservice.productservice.entities.Product;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "ProductProjection", types = Product.class)
public interface ProductProjection {
    String getId();
    String getName();
    String getDescription();
    double getPrice();
    int getQuantity();
    Category getCategory();
    String getImage();
}
