package com.go.it.spring.service;

import com.go.it.spring.dto.CustomerDto;
import com.go.it.spring.entity.CustomerEntity;
import com.go.it.spring.mapper.CustomerMapper;
import com.go.it.spring.repository.CustomerEntityRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerEntityRepository customerEntityRepository;

    public List<CustomerDto> loadAll() {
        List<CustomerEntity> customerEntities = customerEntityRepository.findAll();
        return CustomerMapper.instance().mapEntityToDto(customerEntities);
    }

    public CustomerDto save(CustomerDto customerDto) {
        CustomerEntity saved = customerEntityRepository.save(CustomerMapper.instance().mapDtoToEntity(customerDto));
        return CustomerMapper.instance().mapEntityToDto(saved);
    }

    public void delete(Long id) {
        customerEntityRepository.deleteById(id);
    }

    public CustomerDto update(CustomerDto customerDto) {
        if (customerDto.getId() == null) {
            throw new RuntimeException("ID required");
        }
        CustomerEntity existing = customerEntityRepository.findById(customerDto.getId());
        if (existing == null) {
            throw new RuntimeException("customer not found");
        }
        BeanUtils.copyProperties(customerDto, existing);
        return CustomerMapper.instance().mapEntityToDto(existing);
    }

    @PostConstruct
    public void construct() {
        log.info("CustomerService construct");
    }

    @PreDestroy
    public void destroy() {
        log.info("CustomerService destroy");
    }
}
