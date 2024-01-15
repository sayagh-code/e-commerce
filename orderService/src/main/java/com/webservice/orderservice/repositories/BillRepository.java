package com.webservice.orderservice.repositories;

import com.webservice.orderservice.entities.Bill;
import com.webservice.orderservice.projections.BillProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = BillProjection.class)
public interface BillRepository extends JpaRepository<Bill,Long> {
    List<Bill> findAllByVisitorID(String v);
}