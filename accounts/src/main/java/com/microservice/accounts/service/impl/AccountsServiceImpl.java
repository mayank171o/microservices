package com.microservice.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.accounts.constants.AccountsConstants;
import com.microservice.accounts.dto.AccountsDto;
import com.microservice.accounts.dto.CustomerDto;
import com.microservice.accounts.entity.Accounts;
import com.microservice.accounts.entity.Customer;
import com.microservice.accounts.exception.CustomerAlreadyExistsException;
import com.microservice.accounts.exception.ResourceNotFoundException;
import com.microservice.accounts.mapper.AccountsMapper;
import com.microservice.accounts.mapper.CustomerMapper;
import com.microservice.accounts.repository.AccountsRepository;
import com.microservice.accounts.repository.CustomerRepository;
import com.microservice.accounts.service.IAccountsService;

@Service
public class AccountsServiceImpl implements IAccountsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountsRepository accountsRepository;

	public Customer createCustomer(CustomerDto customerDTO) {

		Customer customer = CustomerMapper.customerMapping(customerDTO, new Customer());
		customer.setCreatedAt(LocalDateTime.now());
		customer.setCreatedBy("Anonymous");
		validateMobile(customer.getMobileNumber());
		return customerRepository.save(customer);

	}

	public void createAccount(CustomerDto customerDTO) {

		Customer customer = createCustomer(customerDTO);
		Accounts account = new Accounts();
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
		account.setAccountNumber(randomAccNumber);
		account.setCreatedBy("Anonymous");
		account.setCreatedAt(LocalDateTime.now());
		account.setBranchAddress(AccountsConstants.ADDRESS);
		account.setAccountType(AccountsConstants.SAVINGS);
		account.setCustomerId(customer.getCustomerId());
		accountsRepository.save(account);

	}

	private void validateMobile(String mobileNumber) {

		System.out.println("mobileNumber is" + mobileNumber);

		if (customerRepository.findByMobileNumber(mobileNumber).isPresent()) {
			throw new CustomerAlreadyExistsException(
					"Customer already registered with given mobileNumber " + mobileNumber);
		}
	}

	@Override
	public CustomerDto getAccountDetails(String mobileNumber) {

		CustomerDto customerDto;
		AccountsDto accountsDto;
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts == null) {
			throw new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString());
		}

		customerDto = CustomerMapper.customerDTOMapping(customer, new CustomerDto());
		accountsDto = AccountsMapper.accountsDTOMapping(accounts, new AccountsDto());
		customerDto.setAccountsDto(accountsDto);
		System.out.println("customer is  " + customer);
		return customerDto;

	}

	public boolean updateAccount(CustomerDto customerDTO) {

		Boolean isUpdated = false;
		;
		AccountsDto accountsDto = customerDTO.getAccountsDto();
		if (accountsDto != null) {
			Accounts account = accountsRepository.findByAccountNumber(accountsDto.getAccountNumber());
			if (account == null) {

				throw new ResourceNotFoundException("Accounts", "AccountNumber",
						accountsDto.getAccountNumber().toString());
			} else {

				Long CustomerId = account.getCustomerId();
				account = AccountsMapper.accountsMapping(accountsDto, account);
				account.setUpdatedAt(LocalDateTime.now());
				account.setUpdatedBy("Mayank");
				accountsRepository.save(account);
				Customer customer = customerRepository.findByCustomerId(CustomerId);
				 if(customer == null)
				 {
					 throw new ResourceNotFoundException("Customer", "CustomerID",
							 CustomerId.toString());
				 }
				customer = CustomerMapper.customerMapping(customerDTO, customer);
				customerRepository.save(customer);
				isUpdated = true;
				

			}
		}

		return isUpdated;
	}

	@Override
	public Boolean deleteAccount(String mobileNumber) {
		
		boolean isdeleted = false;
		
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId());
		 if(account == null)
		 {
			 throw new ResourceNotFoundException("Accounts", "AccountNumber",
						"No Account Associated with this customer " + customer.getCustomerId().toString() );
		 }else
		 {
			 customerRepository.delete(customer);
			 accountsRepository.delete(account);
			 isdeleted = true;
			 
		 }
		return isdeleted;
	}
}
