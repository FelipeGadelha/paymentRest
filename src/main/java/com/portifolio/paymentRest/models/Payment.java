package com.portifolio.paymentRest.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@NotNull
	@Digits(fraction = 2, integer = 20)
	@DecimalMin(value = "0.01")
	private BigDecimal amount;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Client client;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Buyer buyer;
	
	@Enumerated(EnumType.STRING)
	private StatusPayment statusPayment = StatusPayment.WAITING;
	
	private TypePayment typePayment;

	public Payment(BigDecimal amount, Client client, Buyer buyer) {
		this.amount = amount;
		this.client = client;
		this.buyer = buyer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public StatusPayment getStatusPayment() {
		return statusPayment;
	}

	public void setStatusPayment(StatusPayment statusPayment) {
		this.statusPayment = statusPayment;
	}
	public TypePayment getTypePayment() {
		return typePayment;
	}
	public void setTypePayment(TypePayment typePayment) {
		this.typePayment = typePayment;
	}

	@Override
	public String toString() {
		return "Payment [id= " + id + ", amount= " + amount + ", client= " + client + ", buyer= " + buyer
				+ ", statusPayment= " + statusPayment + "]";
	}
	
	
		
	
}
