package com.goit.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private final ProductService productService;

    public OrderService(@Lazy ProductService productService) {
        this.productService = productService;
    }

    public void saveOrder() {
        System.out.println("saving a new order...");
    }
}
