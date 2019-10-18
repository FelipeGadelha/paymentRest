package com.portifolio.paymentRest.models;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.portifolio.paymentRest.enuns.StatusPayment;

@Embeddable
public class Event {
	
	@Enumerated(EnumType.STRING)
	private StatusPayment statusPayment;
	
	private LocalDateTime dataEvent;
	
	public Event() {
		
	}
	
	public Event(StatusPayment statusPayment) {
		this.statusPayment = statusPayment;
		this.dataEvent = LocalDateTime.now();
	}
	
	public StatusPayment getStatusPayment() {
		return statusPayment;
	}
	public LocalDateTime getLocalDateTime() {
		return dataEvent;
	}
	
}
