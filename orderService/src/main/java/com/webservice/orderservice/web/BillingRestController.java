package com.webservice.orderservice.web;

import com.webservice.orderservice.entities.Bill;
import com.webservice.orderservice.feign.ProductRestClient;
import com.webservice.orderservice.feign.VisitorRestClient;
import com.webservice.orderservice.models.Product;
import com.webservice.orderservice.models.Visitor;
import com.webservice.orderservice.repositories.BillRepository;
import com.webservice.orderservice.repositories.ProductItemsRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {
    private BillRepository billRepository;
    private ProductItemsRepository productItemsRepository;
    private VisitorRestClient visitorRestClient;
    private ProductRestClient productRestClient;

    public BillingRestController(BillRepository billRepository, ProductItemsRepository productItemsRepository, VisitorRestClient visitorRestClient, ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.productItemsRepository = productItemsRepository;
        this.visitorRestClient = visitorRestClient;
        this.productRestClient = productRestClient;
    }


    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill=billRepository.findById(id).get();
        Visitor visitor = visitorRestClient.getVisitorById(bill.getVisitorID());
        bill.setVisitor(visitor);
        bill.getProductItems().forEach(p->{
            Product product=productRestClient.getProductById(p.getProductID());
            p.setProductName(product.getName());
        });
        return bill;
    }
}
