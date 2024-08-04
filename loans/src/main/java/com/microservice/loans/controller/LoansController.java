package com.microservice.loans.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.microservice.loans.DTO.LoansDTO;
import com.microservice.loans.DTO.ResponseDTO;
import com.microservice.loans.constants.LoansConstants;
import com.microservice.loans.service.ILoansService;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class LoansController {

	@Autowired
	private ILoansService iLoanService;

	@PostMapping(("/create"))
	public ResponseEntity<ResponseDTO> createLoan(@RequestParam String mobileNumber) {

		System.out.println("Mobile number is  " + mobileNumber);
		iLoanService.createLoan(mobileNumber);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));

	}

	@GetMapping("/fetch")
	public LoansDTO fetchLoanDetails(@RequestParam String mobileNumber) {

		System.out.println("Mobile number is  " + mobileNumber);
		LoansDTO loansDTO = iLoanService.fetchLoan(mobileNumber);
		return loansDTO;
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> updateLoan(@RequestBody LoansDTO LoansDTO) {
		boolean isUpdated = iLoanService.updateLoan(LoansDTO);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
		}

	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDTO> deleteLoan(@RequestParam String mobileNumber)
	{
		Boolean isDeleted = iLoanService.deleteLoan(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
		}

	}
}
