package com.microservice.cards.mapper;

import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.entity.Cards;

public class CardsMapper {

	public static Cards cardsMapping(CardsDto cardsDto, Cards cards) {
		cards.setAmountUsed(cardsDto.getAmountUsed());
		cards.setAvailableAmount(cardsDto.getAvailableAmount());
		cards.setCardType(cardsDto.getCardType());
		cards.setMobileNumber(cardsDto.getMobileNumber());
		cards.setTotalLimit(cardsDto.getTotalLimit());
		return cards;

	}

	public static CardsDto cardsDtoMapping(Cards cards, CardsDto cardsDto) {
		cardsDto.setAmountUsed(cards.getAmountUsed());
		cardsDto.setAvailableAmount(cards.getAvailableAmount());
		cardsDto.setCardNumber(cards.getCardNumber());
		cardsDto.setCardType(cards.getCardType());
		cardsDto.setMobileNumber(cards.getMobileNumber());
		cardsDto.setTotalLimit(cards.getTotalLimit());
		return cardsDto;

	}

}
