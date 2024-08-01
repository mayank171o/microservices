package com.microservice.loans.DTO;

import lombok.Data;

@Data 
public class LoansDTO {

	private String mobileNumber;
	private Long loanNumber;
	private String loanType;
	private int totalLoan;
	private int amountPaid;
	private int outstandingAmount;
	
}
