package com.microservice.loans.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Loans extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="native")
	private Long loanId;
	private String mobileNumber;
	private Long loanNumber;
	private String loanType;
	private int totalLoan;
	private int amountPaid;
	private int outstandingAmount;
	
	

	

}
