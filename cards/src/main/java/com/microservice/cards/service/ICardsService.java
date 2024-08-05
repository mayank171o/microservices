package com.microservice.cards.service;

import org.springframework.stereotype.Service;

import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.entity.Cards;

@Service
public interface ICardsService {

	public Cards createCards(String mobileNumber);

	public Boolean updateCards(CardsDto cardsDto);

	public CardsDto getCardDetails(String mobileNumber);

	public Boolean deleteCard(String mobileNumber);

}
