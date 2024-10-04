package com.microservice.accounts.service;

import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.accounts.dto.CustomerDetailsDTO;

public interface iCustomerService {
	
	public CustomerDetailsDTO fetchCustomerDetails(@RequestParam String mobileNumber);

}
