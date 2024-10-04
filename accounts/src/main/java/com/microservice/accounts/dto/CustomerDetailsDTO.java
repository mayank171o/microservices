package com.microservice.accounts.dto;

import lombok.Data;

@Data	
public class CustomerDetailsDTO {
	
	public String name;
	public String email;
	public String mobileNumber;
	public AccountsDto accountsDto;
	public LoansDto loansDTO;
	public CardsDto cardsDto;

}
