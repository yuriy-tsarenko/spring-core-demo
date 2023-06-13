package com.goit;

import com.goit.service.AuthService;
import com.goit.service.OrderService;
import com.goit.service.OrganizationService;
import com.goit.service.ProductService;
import com.goit.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        OrganizationService organizationService = new OrganizationService();
        ProductService productService = new ProductService(organizationService, null);
        OrderService orderService = new OrderService(productService);
        AuthService authService = new AuthService();
        UserService userService = new UserService(productService, orderService, authService);
        userService.doWork();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.goit");
        UserService userServiceBean = context.getBean(UserService.class);
        userServiceBean.doWork();
        ProductService productServiceBean = context.getBean(ProductService.class);
        productServiceBean.loadProduct();
    }
}