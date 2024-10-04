package com.microservice.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.accounts.dto.CustomerDetailsDTO;
import com.microservice.accounts.service.iCustomerService;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class CustomerController {
	
	@Autowired
	iCustomerService customerService;
	
	@GetMapping("/fetchCustomerDetails")
	public ResponseEntity<CustomerDetailsDTO>  fetchCustomerDetails(@RequestParam String mobileNumber) {

		CustomerDetailsDTO customerDetailsDto = customerService.fetchCustomerDetails(mobileNumber);
		return  ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
	}

}
