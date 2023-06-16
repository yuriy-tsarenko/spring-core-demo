package com.go.it.spring.configuration;

import com.go.it.spring.configuration.hibernate.Datasource;
import com.go.it.spring.repository.CustomerEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public CustomerEntityRepository datasource(Environment environment) {
        return new CustomerEntityRepository(new Datasource(environment));
    }
}
