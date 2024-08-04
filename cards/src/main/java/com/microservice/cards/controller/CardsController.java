package com.microservice.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.entity.Cards;
import com.microservice.cards.service.ICardsService;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class CardsController {

	@Autowired
	ICardsService iCardService;

	@GetMapping("hi")
	public String greetings() {
		return "cello";
	}

	@PostMapping("/create")
	public Cards createCard(@RequestParam String mobileNumber) {

		Cards cards = iCardService.createCards(mobileNumber);
		return cards;

	}
	
	@PutMapping("/update")
	public void updateCards(@RequestBody CardsDto cardsDto )
	{
		iCardService.updateCards(cardsDto);
	}
	
	@GetMapping("/fetch")
	public Cards getCardDetails(@RequestParam String mobileNumber )
	{
		return iCardService.getCardDetails(mobileNumber);
	}
	
	@DeleteMapping("/delete")
	public void DeleteCard(@RequestParam String mobileNumber)
	{
		iCardService.deleteCard(mobileNumber);
	}

}
