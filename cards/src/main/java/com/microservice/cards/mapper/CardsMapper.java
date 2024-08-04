package com.microservice.cards.mapper;

import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.entity.Cards;

public class CardsMapper {

public static Cards cardsMapping(CardsDto cardsDto , Cards cards )
{
	cards.setAmountUsed(cardsDto.getAmountUsed());
	cards.setAvailableAmount(cardsDto.getAvailableAmount());
	cards.setCardType(cardsDto.getCardType());
	cards.setMobileNumber(cardsDto.getMobileNumber());
	cards.setTotalLimit(cardsDto.getTotalLimit());
	return cards;
	
}
	
}
