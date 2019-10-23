package com.portifolio.paymentRest.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.portifolio.paymentRest.enuns.StatusPayment;
import com.portifolio.paymentRest.enuns.TypePayment;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	private BigDecimal amount;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Client client;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Buyer buyer;
	
	@ElementCollection
	@CollectionTable(name = "events",
			joinColumns = @JoinColumn(name = "event_Id"))
	private List<Event> events = new ArrayList<Event>();
	
	@Enumerated(EnumType.STRING)
	private TypePayment typePayment;
	
	public Payment() {
		
	}
	
	public Payment(BigDecimal amount, Client client, Buyer buyer, TypePayment typePayment) {
		this.amount = amount;
		this.client = client;
		this.buyer = buyer;
		this.typePayment = typePayment;
		addEvent(new Event(StatusPayment.CREATED));
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

	public List<Event> getEvents() {
		return events;
	}
	public void addEvent(Event event) {
		events.add(event);
	}
	public TypePayment getTypePayment() {
		return typePayment;
	}
	public void setTypePayment(TypePayment typePayment) {
		this.typePayment = typePayment;
	}

	@Override
	public String toString() {
		return "Payment [id= " + id + ", amount= " + amount + ", client= " + client + ", buyer= " + buyer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((typePayment == null) ? 0 : typePayment.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (typePayment != other.typePayment)
			return false;
		return true;
	}


	
	
}
