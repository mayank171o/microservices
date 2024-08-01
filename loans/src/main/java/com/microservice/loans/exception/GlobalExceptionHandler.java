package com.microservice.loans.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.microservice.loans.DTO.ErrorResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ErrorResponseDTO handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest webRequest) {
		ErrorResponseDTO errResDTO = new ErrorResponseDTO(webRequest.getDescription(false), HttpStatus.NOT_FOUND,
				exception.getMessage(), LocalDateTime.now());
		return errResDTO;

	}
	
	@ExceptionHandler(LoanAlreadyExistsException.class)
	public ErrorResponseDTO handleLoandAlreadyExistsException(LoanAlreadyExistsException exception,
			WebRequest webRequest) {
		ErrorResponseDTO errResDTO = new ErrorResponseDTO(webRequest.getDescription(false), HttpStatus.NOT_FOUND,
				exception.getMessage(), LocalDateTime.now());
		return errResDTO;

	}
	@ExceptionHandler(Exception.class)
	public ErrorResponseDTO handleGlobalException(Exception exception,
			WebRequest webRequest) {
		ErrorResponseDTO errResDTO = new ErrorResponseDTO(webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,
				exception.getMessage(), LocalDateTime.now());
		return errResDTO;

	}

}
