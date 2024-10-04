package com.microservice.accounts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.accounts.dto.AccountsDto;
import com.microservice.accounts.dto.CardsDto;
import com.microservice.accounts.dto.CustomerDetailsDTO;
import com.microservice.accounts.dto.LoansDto;
import com.microservice.accounts.repository.AccountsRepository;
import com.microservice.accounts.repository.CustomerRepository;
import com.microservice.accounts.service.iCustomerService;
import com.microservice.accounts.service.client.CardsFeignClient;
import com.microservice.accounts.service.client.LoansFeignClient;
import com.microservice.accounts.entity.Accounts;
import com.microservice.accounts.entity.Customer;
import com.microservice.accounts.exception.ResourceNotFoundException;
import com.microservice.accounts.mapper.AccountsMapper;
import com.microservice.accounts.mapper.CustomerMapper;

@Service
public class CustomerServiceImpl implements iCustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private CardsFeignClient cardsConnector;

	@Autowired
	private LoansFeignClient loansConnector;

	@Override
	public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId());
		CustomerDetailsDTO customerDetailsDto = CustomerMapper.customerDetailsDTOMapping(customer,
				new CustomerDetailsDTO());
		AccountsDto accountsDto = AccountsMapper.accountsDTOMapping(account, new AccountsDto());
		CardsDto cardsDto = cardsConnector.getCardDetails(mobileNumber);
		LoansDto loansDto = loansConnector.fetchLoanDetails(mobileNumber);
		customerDetailsDto.setAccountsDto(accountsDto);
		customerDetailsDto.setCardsDto(cardsDto);
		customerDetailsDto.setLoansDTO(loansDto);

		return customerDetailsDto;
	}

}
