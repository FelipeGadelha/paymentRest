package com.portifolio.paymentRest.dto;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PaymentCardDtoTest {	
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	@TestConfiguration
	static class Config{
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder();	
		}
	}
	
	@Test
	public void validatesCardWhenAmountIsNullShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(null, "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("amount", "amount is required");
	}
	@Test
	public void validatesCardWhenAmountIsNegativeShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(-10.0), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("amount", "must be greater than or equal to 0.01");
	}
	@Test
	public void validatesCardWhenAmountIsExceedsTheFractionalLimitShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(10.111), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("amount", "numeric value out of limit (<20 integers>, <2 fractioned> expected)");
	}
	@Test
	public void validatesCardWhenAmountIsExceedsTheIntegerLimitShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(100000000000000000000.11), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("amount", "numeric value out of limit (<20 integers>, <2 fractioned> expected)");
	}
	
	@Test
	public void validatesCardWhenClientNameIsNullShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(50.0), null, "felipe", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("clientName", "customer name is required");
	}
	
	@Test
	public void validatesCardWhenClientNameIsBlankShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(50.0), "", "felipe", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("clientName", "customer name is required");
	}
	
	@Test
	public void validatesCardWhenBuyerNameIsNullShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", null, "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerName", "buyer name is required");
	}
	@Test
	public void validatesCardWhenBuyerNameIsBlankShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerName", "buyer name is required");
	}
	@Test
	public void validatesCardWhenBuyerEmailIsNullShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", null, "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerEmail", "E-mail is required");
	}
	@Test
	public void validatesCardWhenBuyerEmailIsBlankShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerEmail", "E-mail is required");
	}
	@Test
	public void validatesCardWhenBuyerEmailHasNoAtSignShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "felipegadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerEmail", "E-mail is required");
	}
	@Test
	public void validatesCardWhenBuyerCpfIsNullShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", null, "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerCpf", "cpf is required");
	}
	@Test
	public void validatesCardWhenBuyerCpfIsBlankShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", "", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerCpf", "Invalid CPF");
	}
	@Test
	public void validatesCardWhenBuyerCpfIsInvalidShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", "111.111.111-11", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("buyerCpf", "Invalid CPF");
	}
	
	@Test
	public void validatesCardWhenCardHolderNameIsNullShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68", null, "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("cardHolderName", "card hoder name is required");
	}
	
	@Test
	public void validatesCardWhenCardHolderNameIsBlankShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68", "", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("cardHolderName", "card hoder name is required");
	}
	@Test
	public void validatesCardWhenCardNumberIsNullShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", null, LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("cardNumber", "card number is required");
	}
	
	@Test
	public void validatesCardWhenCardNumberIsBlankShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "", LocalDate.of(2021, 02, 05), "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("cardNumber", "invalid identification code");
	}
	
	@Test
	public void validatesCardWhenCardExpirationDateIsNullShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", null, "651");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("cardExpirationDate", "this field is required");
	}
	@Test
	public void validatesCardWhenJsonFormatPatternCardExpirationDateIsInvalidShouldReturnStatusCode400BadRequest() throws Exception {
		String uri = "/v1/payment/creditCard/";
		String content = " {\r\n" + 
				"        \"amount\": 50.50,\r\n" + 
				"        \"clientName\": \"gian carlos\",\r\n" + 
				"        \"buyerName\": \"luis\",\r\n" + 
				"        \"buyerEmail\": \"luis@feitosa.com\",\r\n" + 
				"        \"buyerCpf\":\"337.208.670-80\",\r\n" + 
				"        \"cardHolderName\": \"felipe gadelha\",\r\n" + 
				"        \"cardNumber\": \"5417-5541-4998-8091\",\r\n" + 
				"        \"expiration_Date\": \"2021/02/02\",\r\n" + 
				"        \"cardCvv\": \"651\"\r\n" + 
				"    }";
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	
	@Test
	public void validatesCardWhenCardCvvIsNullShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), null);
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("cardCvv", "cvv must contain 3 numbers");
	}
	
	@Test
	public void validatesCardWhenCardCvvIsBlankShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("cardCvv", "cvv must contain 3 numbers");
	}
	@Test
	public void validatesCardWhenCardCvvIsSmaller3ShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "12");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("cardCvv", "cvv must contain 3 numbers");
	}
	
	@Test
	public void validatesCardWhenCardCvvIsLarger3ShouldReturnStatusCode400BadRequest() {
		PaymentCardDto cardDto = new PaymentCardDto(new BigDecimal(20.0), "isabella", "felipe", "felipe@gadelha.com", "990.977.290-68", "felipe gadelha", "5417-5541-4998-8091", LocalDate.of(2021, 02, 05), "1234");
		ResponseEntity<String> entity = restTemplate.postForEntity("/v1/payment/creditCard/", cardDto, String.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(400);
		assertThat(entity.getBody()).contains("cardCvv", "cvv must contain 3 numbers");
	}

}
