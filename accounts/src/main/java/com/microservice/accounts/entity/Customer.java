package com.microservice.accounts.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Customer extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="native")
	public Long customerId;
	public String name;
	public String email;
	public String mobileNumber;

}
