package com.portifolio.paymentRest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.portifolio.paymentRest.dto.PaymentBoletoDto;
import com.portifolio.paymentRest.dto.PaymentCardDto;
import com.portifolio.paymentRest.models.PaymentBoleto;
import com.portifolio.paymentRest.models.PaymentCard;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class PaymentIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@TestConfiguration
	static class Config {
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder();
		}
	}
	
	@Test
	public void createPaymentBoletoWhenShouldReturnStatusCode201Created() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(40.0), "isabella", "felipe",
				"felipe@gadelha.com", "337.208.670-80");
		ResponseEntity<PaymentBoleto> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, PaymentBoleto.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(201);
		assertThat(entity.getBody()).isNotNull();
	}

	@Test
	public void getPaymentBoletoWhenIdInvalidShouldReturnStatusCode404NotFound() {
		ResponseEntity<PaymentBoleto> entity = restTemplate.getForEntity("/v1/payment/boleto/{id}", PaymentBoleto.class, -1L);
		assertThat(entity.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void getPaymentBoletoShouldReturnStatusCode200Ok() {
		ResponseEntity<PaymentBoleto> entity = restTemplate.getForEntity("/v1/payment/boleto/{id}", PaymentBoleto.class, 1L);
		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void createPaymentCardWhenShouldReturnStatusCode201Created() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(40.5), "isabella", "felipe", "felipe@gadelha.com",
				"990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(201);
	}

	@Test
	public void getPaymentCardWhenIdInvalidShouldReturnStatusCode404NotFound() {
		ResponseEntity<PaymentCard> entity = restTemplate.getForEntity("/v1/payment/creditCard/{id}", PaymentCard.class, -1L);
		assertThat(entity.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void getPaymentCardShouldReturnStatusCode200Ok() {
		ResponseEntity<PaymentCard> entity = restTemplate.getForEntity("/v1/payment/boleto/{id}", PaymentCard.class, 1L);
		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
	}

}
