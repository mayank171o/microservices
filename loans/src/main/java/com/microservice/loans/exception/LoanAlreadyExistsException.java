package com.microservice.loans.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistsException extends RuntimeException {

   private static final long serialVersionUID = 1L;

public LoanAlreadyExistsException(String message)
   {
	   super(message);
   }
}
