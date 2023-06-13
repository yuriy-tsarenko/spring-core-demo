package com.goit.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    private final OrganizationService organizationService;
    private OrderService orderService;

    public ProductService(OrganizationService organizationService, @Lazy OrderService orderService) {
        this.organizationService = organizationService;
        this.orderService = orderService;
    }

    public void loadProduct() {
        orderService.saveOrder();
    }
}
