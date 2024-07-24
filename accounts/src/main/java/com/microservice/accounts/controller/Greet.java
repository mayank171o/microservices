package com.microservice.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greet {
	
	@GetMapping("/hello")
	public String sayHello()
	{
		return "hi";
	}

}
