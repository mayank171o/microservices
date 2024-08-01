package com.microservice.loans.mapper;

import com.microservice.loans.DTO.LoansDTO;
import com.microservice.loans.entity.Loans;

public class LoansMapper {
	
	public static Loans loansMapping(LoansDTO loansDTO,Loans loans)
	{
		loans.setAmountPaid(loansDTO.getAmountPaid());
		loans.setLoanNumber(loansDTO.getLoanNumber());
		loans.setLoanType(loansDTO.getLoanType());
		loans.setMobileNumber(loansDTO.getMobileNumber());
		loans.setOutstandingAmount(loansDTO.getOutstandingAmount());
		loans.setTotalLoan(loansDTO.getTotalLoan());
		return loans;
		
	}
	
	public static LoansDTO loansDTOMapping(Loans loans, LoansDTO loansDTO)
	{
		loansDTO.setAmountPaid(loans.getAmountPaid());
		loansDTO.setLoanNumber(loans.getLoanNumber());
		loansDTO.setLoanType(loans.getLoanType());
		loansDTO.setMobileNumber(loans.getMobileNumber());
		loansDTO.setOutstandingAmount(loans.getOutstandingAmount());
		loansDTO.setTotalLoan(loans.getTotalLoan());
		return loansDTO;
		
	}

}
