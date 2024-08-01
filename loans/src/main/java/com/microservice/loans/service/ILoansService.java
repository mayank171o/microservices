package com.microservice.loans.service;

import org.springframework.stereotype.Service;

import com.microservice.loans.DTO.LoansDTO;
import com.microservice.loans.entity.Loans;

@Service
public interface ILoansService {

	public Loans createLoan(String mobileNumber);

	public LoansDTO fetchLoan(String mobileNumber);

	public Boolean updateLoan(LoansDTO loansDTO);

	public Boolean deleteLoan(String mobileNumber);
}
