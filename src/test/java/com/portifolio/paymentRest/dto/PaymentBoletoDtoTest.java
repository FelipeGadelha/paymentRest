package com.portifolio.paymentRest.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

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
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PaymentBoletoDtoTest {	
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@TestConfiguration
	static class Config{
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder();	
		}
	}
	
	@Test
	public void validatesBoletoWhenAmountIsNullShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(null, "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("amount", "amount is required");
	}
	@Test
	public void validatesBoletoWhenAmountIsNegativeShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(-10.0), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("amount", "must be greater than or equal to 0.01");
	}
	@Test
	public void validatesBoletoWhenAmountIsExceedsTheFractionalLimitShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(10.111), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("amount", "numeric value out of limit (<20 integers>, <2 fractioned> expected)");
	}
	@Test
	public void validatesBoletoWhenAmountIsExceedsTheIntegerLimitShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(100000000000000000000.11), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("amount", "numeric value out of limit (<20 integers>, <2 fractioned> expected)");
	}
	
	@Test
	public void validatesBoletoWhenClientNameIsNullShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(50.0), null, "felipe", "felipe@gadelha.com", "990.977.290-68");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("clientName", "customer name is required");
	}
	
	@Test
	public void validatesBoletoWhenClientNameIsBlankShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(50.0), "", "felipe", "felipe@gadelha.com", "990.977.290-68");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("clientName", "customer name is required");
	}
	
	@Test
	public void validatesBoletoWhenBuyerNameIsNullShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(20.0), "isabella", null, "felipe@gadelha.com", "990.977.290-68");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerName", "buyer name is required");
	}
	@Test
	public void validatesBoletoWhenBuyerNameIsBlankShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(20.0), "isabella", "", "felipe@gadelha.com", "990.977.290-68");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerName", "buyer name is required");
	}
	@Test
	public void validatesBoletoWhenBuyerEmailIsNullShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(20.0), "isabella", "felipe", null, "990.977.290-68");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerEmail", "E-mail is required");
	}
	@Test
	public void validatesBoletoWhenBuyerEmailIsBlankShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(20.0), "isabella", "felipe", "", "990.977.290-68");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerEmail", "E-mail is required");
	}
	@Test
	public void validatesBoletoWhenBuyerEmailHasNoAtSignShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(20.0), "isabella", "felipe", "felipegadelha.com", "990.977.290-68");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerEmail", "E-mail is required");
	}
	@Test
	public void validatesBoletoWhenBuyerCpfIsNullShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", null);
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerCpf", "cpf is required");
	}
	@Test
	public void validatesBoletoWhenBuyerCpfIsBlankShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", "");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerCpf", "Invalid CPF");
	}
	@Test
	public void validatesBoletoWhenBuyerCpfIsInvalidShouldReturnStatusCode400BadRequest() {
		PaymentBoletoDto boletoDto = new PaymentBoletoDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", "111.111.111-11");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/boleto/", boletoDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerCpf", "Invalid CPF");
	}
	

}
