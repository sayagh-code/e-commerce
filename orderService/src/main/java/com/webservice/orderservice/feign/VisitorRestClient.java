package com.webservice.orderservice.feign;

import com.webservice.orderservice.models.Visitor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface VisitorRestClient {
    @GetMapping(path = "/visitors/{id}")
    Visitor getVisitorById(@PathVariable(name = "id") Long id);
}
