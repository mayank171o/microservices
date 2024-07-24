package com.microservice.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Accounts extends BaseEntity {
	
	public Long customerId;
	
	@Id
	public Long accountNumber;
	public String accountType;
	public String branchAddress;
	

}
