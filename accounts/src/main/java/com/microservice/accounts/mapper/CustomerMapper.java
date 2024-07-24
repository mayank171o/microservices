package com.microservice.accounts.mapper;

import java.time.LocalDateTime;

import com.microservice.accounts.dto.CustomerDto;

import com.microservice.accounts.entity.Customer;
import com.microservice.accounts.repository.CustomerRepository;

public class CustomerMapper {

	Customer customer;

	public static Customer customerMapping(CustomerDto customerDTO, Customer customer) {

		customer.setEmail(customerDTO.getEmail());
		customer.setMobileNumber(customerDTO.getMobileNumber());
		customer.setName(customerDTO.getName());

		return customer;

	}

	public static CustomerDto customerDTOMapping(Customer customer, CustomerDto customerDto) {

		customerDto.setEmail(customer.getEmail());
		customerDto.setMobileNumber(customer.getMobileNumber());
		customerDto.setName(customer.getName());
		customerDto.setAccountsDto(null);
		return customerDto;

	}

}
