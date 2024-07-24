package com.microservice.accounts.dto;

import lombok.Data;

@Data
public class CustomerDto {
	

	public String name;
	public String email;
	public String mobileNumber;
	public AccountsDto accountsDto;

}
