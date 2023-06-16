package com.go.it.spring.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserAccountDto {

    private String username;
    private String password;
    private CustomerDto customer;
}
