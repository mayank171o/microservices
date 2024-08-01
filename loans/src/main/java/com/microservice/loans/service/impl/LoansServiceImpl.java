package com.microservice.loans.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.loans.DTO.LoansDTO;
import com.microservice.loans.constants.LoansConstants;
import com.microservice.loans.entity.Loans;
import com.microservice.loans.exception.LoanAlreadyExistsException;
import com.microservice.loans.exception.ResourceNotFoundException;
import com.microservice.loans.mapper.LoansMapper;
import com.microservice.loans.repository.LoansRepository;
import com.microservice.loans.service.ILoansService;

@Service
public class LoansServiceImpl implements ILoansService {

	@Autowired
	private LoansRepository loansRepository;

	@Override
	public Loans createLoan(String mobileNumber) {

		if (loansRepository.findByMobileNumber(mobileNumber) != null) {
			
			throw new LoanAlreadyExistsException("Loan Already exists with this mobile number");
		
		} else {
			return createNewLoan(mobileNumber);
		}

	}

	private Loans createNewLoan(String mobileNumber) {
		Loans loans = new Loans();
		long randomLoanNumber = 1000000000L + new Random().nextInt(900000000);
		loans.setMobileNumber(mobileNumber);
		loans.setLoanType(LoansConstants.HOME_LOAN);
		loans.setLoanNumber(randomLoanNumber);
		loans.setAmountPaid(0);
		loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
		loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
		loans.setCreatedBy("System");
		loans.setCreatedAt(LocalDateTime.now());
		return loansRepository.save(loans);

	}

	@Override
	public LoansDTO fetchLoan(String mobileNumber) {
		Loans loans = loansRepository.findByMobileNumber(mobileNumber);

		if (loans == null) {
			throw new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber);
		} else {
			return LoansMapper.loansDTOMapping(loans, new LoansDTO());

		}

	}

	@Override
	public Boolean updateLoan(LoansDTO loansDTO) {
		Loans loans = loansRepository.findByLoanNumber(loansDTO.getLoanNumber());
		if (loans == null) {
			throw new ResourceNotFoundException("Loan", "LoanNumber", loansDTO.getLoanNumber().toString());

		} else {
			LoansMapper.loansMapping(loansDTO, loans);
			loansRepository.save(loans);
			return true;
		}

	}

	@Override
	public Boolean deleteLoan(String mobileNumber) {
		
		if (loansRepository.findByMobileNumber(mobileNumber) ==null)
		{
			throw new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber);
		}else
		{
			loansRepository.delete(loansRepository.findByMobileNumber(mobileNumber));
		}
		return null;
	}

}
