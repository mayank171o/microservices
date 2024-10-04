package com.microservice.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.accounts.dto.CardsDto;



@FeignClient("cards")
public interface CardsFeignClient {
	
	@GetMapping("/api/fetch")
	public CardsDto getCardDetails(@RequestParam String mobileNumber);
}
