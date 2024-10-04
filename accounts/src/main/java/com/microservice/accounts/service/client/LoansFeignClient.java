package com.microservice.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.accounts.dto.LoansDto;



@FeignClient("loans")
public interface LoansFeignClient {

	@GetMapping("/api/fetch")
	public LoansDto fetchLoanDetails(@RequestParam String mobileNumber) ;
}
