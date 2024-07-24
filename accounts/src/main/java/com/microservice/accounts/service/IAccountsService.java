package com.microservice.accounts.service;

import com.microservice.accounts.dto.CustomerDto;

public interface IAccountsService {
	
	public void createAccount(CustomerDto customerDTO);

	public CustomerDto getAccountDetails(String mobileNumber);

	public boolean updateAccount(CustomerDto customerDTO);

	public Boolean deleteAccount(String mobileNumber);
	

}
