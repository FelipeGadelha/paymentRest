package com.portifolio.paymentRest.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.portifolio.paymentRest.models.Boleto;
import com.portifolio.paymentRest.models.Buyer;
import com.portifolio.paymentRest.models.Client;

public class BoletoDto {
	
	@NotNull(message = "amount is required")
	@Digits(integer = 20, fraction = 2, message = "numeric value out of limit (<20 integers>, <2 fractioned> expected)")
	@DecimalMin(value = "0.01", message = "must be greater than or equal to 0.01")
	private BigDecimal amount;
	
	@NotNull(message = "customer name is required")
	@NotBlank(message = "customer name is required")
	private String clientName;
	@NotNull(message = "buyer name is required")
	@NotBlank(message = "buyer name is required")
	private String buyerName;
	@NotNull(message = "E-mail is required")
	@NotBlank(message = "E-mail is required")
	@Email(message = "E-mail is required")
	private String buyerEmail;
	@NotNull(message = "cpf is required")
	@CPF(message = "Invalid CPF")
	private String buyerCpf;

	public BoletoDto(BigDecimal amount, String clientName, String buyerName, String buyerEmail, String buyerCpf) {
		this.amount = amount;
		this.clientName = clientName;
		this.buyerName = buyerName;
		this.buyerEmail = buyerEmail;
		this.buyerCpf = buyerCpf;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getBuyerCpf() {
		return buyerCpf;
	}

	public void setBuyerCpf(String buyerCpf) {
		this.buyerCpf = buyerCpf;
	}
	
	public Buyer extractBuyer(){
		return new Buyer(getBuyerName(), 
				getBuyerEmail(), 
				getBuyerCpf());
	}
	public Client extractClient() {
		return new Client(getClientName());
	}
	
	public Boleto extractBoleto() {
		return new Boleto(getAmount(), extractClient(), extractBuyer());
	}
	
}
