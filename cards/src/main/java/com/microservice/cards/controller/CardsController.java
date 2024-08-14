package com.microservice.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.cards.constants.CardsConstants;
import com.microservice.cards.dto.CardsContactInfoDto;
import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.dto.ResponseDto;
import com.microservice.cards.service.ICardsService;


@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class CardsController {

	@Autowired
	ICardsService iCardService;
	
	@Value("${build.version}")
	private String version;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	CardsContactInfoDto cardsContactInfoDto;

	@GetMapping("hi")
	public String greetings() {
		return "cello";
	}

	@PostMapping("/create")
	public ResponseDto createCard(@RequestParam String mobileNumber) {

		iCardService.createCards(mobileNumber);
		return new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201);

	}

	@PutMapping("/update")
	public ResponseDto updateCards(@RequestBody CardsDto cardsDto) {
		if (iCardService.updateCards(cardsDto)) {
			return new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200);
		} else {
			return new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE);
		}
	}

	@GetMapping("/fetch")
	public CardsDto getCardDetails(@RequestParam String mobileNumber) {
		return iCardService.getCardDetails(mobileNumber);
	}

	@DeleteMapping("/delete")
	public ResponseDto DeleteCard(@RequestParam String mobileNumber) {
		if (iCardService.deleteCard(mobileNumber)) {
			return new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200);
		} else {
			return new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE);
		}
	}
	
	@GetMapping("/version")
	public String getVersion() {
		return version;

	}
	
	@GetMapping("/shell")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("SHELL"));
    }
	
	@GetMapping("/get-contact")
    public ResponseEntity<CardsContactInfoDto> getContact() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsContactInfoDto);
    }

}
