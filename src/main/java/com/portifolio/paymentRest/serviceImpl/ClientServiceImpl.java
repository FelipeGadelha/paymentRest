package com.portifolio.paymentRest.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.paymentRest.models.Client;
import com.portifolio.paymentRest.repository.ClientRepository;
import com.portifolio.paymentRest.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private final ClientRepository clientRepository;
	
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client identifyClient(Client client) {
		Optional<Client> clientId = clientRepository.findByName(client.getName());
		if (clientId.isPresent()) {
			return clientId.get();
		}
		return client;
		
		
	}

	
}
