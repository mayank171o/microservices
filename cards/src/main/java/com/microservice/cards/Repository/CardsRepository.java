package com.microservice.cards.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.cards.entity.Cards;

@Repository
public interface CardsRepository extends JpaRepository<Cards,Long> {
  
	public Cards findByMobileNumber(String mobileNumber);
	public Cards findByCardNumber(String cardNumber);
  
}
