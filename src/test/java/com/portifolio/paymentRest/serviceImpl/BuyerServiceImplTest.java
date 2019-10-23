package com.portifolio.paymentRest.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.portifolio.paymentRest.models.Buyer;
import com.portifolio.paymentRest.repository.BuyerRepository;
import com.portifolio.paymentRest.serviceImpl.BuyerServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(value = "test")
public class BuyerServiceImplTest {
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@Before
	public void createdClient() {
		Buyer buyer = new Buyer("felipe", "felipe@gadelha.com", "864.153.990-55"); 
		buyerRepository.save(buyer);
	}
	@Test
	public void shouldReturnBuyerWithoutId() {
		BuyerServiceImpl buyerServiceImpl = new BuyerServiceImpl(buyerRepository);
		Buyer buyer = new Buyer("felipe", "felipe@diniz.com", "055.853.590-96");
		Buyer identifyBuyer = buyerServiceImpl.identifyBuyer(buyer);
		assertThat(identifyBuyer.getId()).isNull();
	}
	
	@Test
	public void shouldReturnBuyerWithTheIdRegistredInSistem() {
		BuyerServiceImpl buyerServiceImpl = new BuyerServiceImpl(buyerRepository);
		Buyer buyer = new Buyer("felipe", "felipe@gadelha.com", "864.153.990-55");
		Buyer identifyBuyer = buyerServiceImpl.identifyBuyer(buyer);
		assertThat(identifyBuyer.getId()).isEqualTo(1);
	}
	
	

}
