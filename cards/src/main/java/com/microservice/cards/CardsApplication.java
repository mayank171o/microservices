package com.microservice.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.microservice.cards.dto.CardsContactInfoDto;

@SpringBootApplication
@EnableConfigurationProperties(CardsContactInfoDto.class)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
