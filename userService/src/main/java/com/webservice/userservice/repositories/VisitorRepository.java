package com.webservice.userservice.repositories;

import com.webservice.userservice.entities.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VisitorRepository extends JpaRepository<Visitor, Long>{
    Visitor findVisitorByUsername(String name);
}
