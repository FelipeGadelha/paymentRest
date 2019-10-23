package com.portifolio.paymentRest.service;

import org.springframework.stereotype.Service;

import com.portifolio.paymentRest.models.Client;
@Service
public interface ClientService {

	Client identifyClient(Client client);

}
