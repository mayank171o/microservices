package com.microservice.accounts.mapper;

import java.time.LocalDateTime;
import java.util.Random;

import com.microservice.accounts.constants.AccountsConstants;
import com.microservice.accounts.dto.AccountsDto;
import com.microservice.accounts.entity.Accounts;
import com.microservice.accounts.entity.Customer;

public class AccountsMapper {

	public static Accounts accountsMapping(AccountsDto accountsDto, Accounts account) {

		account.setAccountType(accountsDto.getAccountType());
		account.setBranchAddress(accountsDto.getBranchAddress());
		account.setAccountNumber(accountsDto.getAccountNumber());
		return account;

	}

	public static AccountsDto accountsDTOMapping(Accounts accounts, AccountsDto accountsDto) {

		accountsDto.setAccountNumber(accounts.getAccountNumber());
		accountsDto.setAccountType(accounts.getAccountType());
		accountsDto.setBranchAddress(accounts.getBranchAddress());
		return accountsDto;
	}

}
