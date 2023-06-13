package com.goit.service;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final ProductService productService;
    private final OrderService orderService;
    private final AuthService authService;

    public UserService(ProductService productService, OrderService orderService, AuthService authService) {
        this.productService = productService;
        this.orderService = orderService;
        this.authService = authService;
    }

    public void doWork() {
        System.out.println("UserService works...");
    }
}
