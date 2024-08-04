package com.microservice.cards.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.cards.Repository.CardsRepository;
import com.microservice.cards.constants.CardsConstants;
import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.entity.Cards;
import com.microservice.cards.exceptions.CardAlreadyExistsException;
import com.microservice.cards.exceptions.ResourceNotFoundException;
import com.microservice.cards.mapper.CardsMapper;
import com.microservice.cards.service.ICardsService;

@Service
public class ICardsServiceImpl implements ICardsService {
	@Autowired
	CardsRepository cardsRepository;

	@Override
	public Cards createCards(String mobileNumber) {

		if (cardsRepository.findByMobileNumber(mobileNumber) != null) {
			throw new CardAlreadyExistsException("Cards Already Exist");
		} else {
			return createNewCard(mobileNumber);

		}
	}

	private Cards createNewCard(String mobileNumber) {
		Cards cards = new Cards();
		long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
		cards.setCardNumber(Long.toString(randomCardNumber));
		cards.setMobileNumber(mobileNumber);
		cards.setCardType(CardsConstants.CREDIT_CARD);
		cards.setAmountUsed(0);
		cards.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
		cards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
		cards.setCreatedBy("System");
		cards.setCreatedAt(LocalDateTime.now());
		return cardsRepository.save(cards);

	}

	@Override
	public void updateCards(CardsDto cardsDto) {
		Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber());

		if (cards == null) {
			throw new ResourceNotFoundException("Card", "CardNUmber", cardsDto.getCardNumber());
		} else {

			cards.setUpdatedAt(LocalDateTime.now());
			cards.setUpdatedBy("System Update");
			cardsRepository.save(CardsMapper.cardsMapping(cardsDto, cards));
		}
	}

	@Override
	public Cards getCardDetails(String mobileNumber) {
		if(cardsRepository.findByMobileNumber(mobileNumber) == null)
		{
			throw new ResourceNotFoundException("Cards", "mobile number", mobileNumber);
		}else
		{
			return cardsRepository.findByMobileNumber(mobileNumber);
		}

	}

	@Override
	public void deleteCard(String mobileNumber) {
		
		Cards cards = cardsRepository.findByMobileNumber(mobileNumber);
		if(cards == null)
		{
			throw new ResourceNotFoundException("Cards", "mobile number", mobileNumber);
		}else
		{
			cardsRepository.delete(cards);
		}
		
	}

}
