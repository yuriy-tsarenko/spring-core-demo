package com.go.it.spring.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {

    @PostConstruct
    public void construct() {
        log.info("CustomerService construct");
    }

    @PreDestroy
    public void destroy() {
        log.info("CustomerService destroy");
    }
}
