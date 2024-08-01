package com.microservice.loans.entity;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
public class BaseEntity {

	@Column(updatable = false)
	private LocalDateTime createdAt;

	@Column(insertable = false)
	private LocalDateTime updateAt;

	@Column(updatable = false)
	private String createdBy;

	@Column(insertable = false)
	private String updatedBy;

}
