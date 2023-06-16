package com.go.it.spring.repository;

import com.go.it.spring.configuration.hibernate.Datasource;
import com.go.it.spring.configuration.hibernate.HibernateAbstractRepository;
import com.go.it.spring.entity.CustomerEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerEntityRepository extends HibernateAbstractRepository<CustomerEntity, Long> {

    public CustomerEntityRepository(Datasource datasource) {
        super(datasource);
    }
}
