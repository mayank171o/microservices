package com.microservice.accounts.dto;

import lombok.Data;

@Data 
public class LoansDto {

	private String mobileNumber;
	private Long loanNumber;
	private String loanType;
	private int totalLoan;
	private int amountPaid;
	private int outstandingAmount;
	
}
