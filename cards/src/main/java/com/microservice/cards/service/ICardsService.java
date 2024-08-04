package com.microservice.cards.service;

import org.springframework.stereotype.Service;

import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.entity.Cards;

@Service
public interface ICardsService {

	public Cards createCards(String mobileNumber);

	public void updateCards(CardsDto cardsDto);

	public Cards getCardDetails(String mobileNumber);

	public void deleteCard(String mobileNumber);

}
