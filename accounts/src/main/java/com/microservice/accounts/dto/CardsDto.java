package com.microservice.accounts.dto;

import lombok.Data;


@Data
public class CardsDto {

private String cardNumber;
private String mobileNumber;
private String cardType;
private int totalLimit;
private int amountUsed;
private int availableAmount;


}
