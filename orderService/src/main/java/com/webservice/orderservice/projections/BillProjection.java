package com.webservice.orderservice.projections;

import com.webservice.orderservice.entities.Bill;
import com.webservice.orderservice.entities.ProductItems;
import com.webservice.orderservice.models.Visitor;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@Projection(name = "ProductProjection", types = Bill.class)
public interface BillProjection {
    Long getId();
    Date getOrderDate();
    Collection<ProductItems> getProductItems();
    String getVisitorID();
}
