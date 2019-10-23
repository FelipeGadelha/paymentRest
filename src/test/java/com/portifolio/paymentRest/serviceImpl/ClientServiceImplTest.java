package com.portifolio.paymentRest.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.portifolio.paymentRest.models.Client;
import com.portifolio.paymentRest.repository.ClientRepository;
import com.portifolio.paymentRest.serviceImpl.ClientServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(value = "test")
public class ClientServiceImplTest {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Before
	public void createdClient() {
		Client client = new Client("felipe"); 
		clientRepository.save(client);
	}
	@Test
	public void shouldReturnCustomerWithoutId() {
		ClientServiceImpl clientServiceImpl = new ClientServiceImpl(clientRepository);
		Client client = new Client("isabella"); 
		Client identifyClient = clientServiceImpl.identifyClient(client);
		assertThat(identifyClient.getId()).isNull();
	}
	
	@Test
	public void shouldReturnCustomerWithTheIdRegistredInSistem() {
		ClientServiceImpl clientServiceImpl = new ClientServiceImpl(clientRepository);
		Client client = new Client("felipe"); 
		Client identifyClient = clientServiceImpl.identifyClient(client);
		assertThat(identifyClient.getId()).isEqualTo(2);
	}
	
	
	
	
	
}
