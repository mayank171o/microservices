package com.microservice.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.accounts.constants.AccountsConstants;
import com.microservice.accounts.dto.AccountsContactInfoDto;
import com.microservice.accounts.dto.CustomerDto;
import com.microservice.accounts.dto.ResponseDto;
import com.microservice.accounts.service.IAccountsService;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })

public class AccountsController {

	@Autowired
	private IAccountsService iAccountsService;

	//@Value("${build.version}")
	private String version;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AccountsContactInfoDto accountsContactInfoDto;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDTO) {

		// serviceImpl = new AccountsServiceImpl();
		iAccountsService.createAccount(customerDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));

	}

	@GetMapping("/fetch")
	public CustomerDto fetchAccount(@RequestParam String mobileNumber) {

		CustomerDto customerDto = iAccountsService.getAccountDetails(mobileNumber);
		return customerDto;
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDTO) {

		Boolean isUpdated;
		isUpdated = iAccountsService.updateAccount(customerDTO);

		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
		}

	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber) {

		Boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
		}

	}

	@GetMapping("/version")
	public String getVersion() {
		return version;

	}
	
	@GetMapping("/shell")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("SHELL"));
    }
	
	@GetMapping("/get-contact")
    public ResponseEntity<AccountsContactInfoDto> getContact() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }
	

}
