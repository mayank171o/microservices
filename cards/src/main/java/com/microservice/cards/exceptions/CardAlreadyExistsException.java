package com.microservice.cards.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CardAlreadyExistsException(String message)
	{
		super(message);
	}

}
