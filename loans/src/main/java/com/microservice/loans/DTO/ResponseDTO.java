package com.microservice.loans.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor
public class ResponseDTO {

 private String statusMessage;
 
 private String statusCode;
 
}
