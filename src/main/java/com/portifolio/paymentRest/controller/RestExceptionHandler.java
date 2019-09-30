package com.portifolio.paymentRest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.portifolio.paymentRest.error.ErrorDetails;
import com.portifolio.paymentRest.error.ValidationErrorDetails;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
		
		List<String> fields = fieldErrors.stream().map(f -> f.getField()).collect(Collectors.toList());
		List<String> fieldMessages = fieldErrors.stream().map(f -> f.getDefaultMessage()).collect(Collectors.toList());
		
		Map<List<String>, List<String>> map = new HashMap<>();
		map.put(fields, fieldMessages);
		
		
		
		ValidationErrorDetails validationErrorDetails = ValidationErrorDetails.Builder.newBuilder()
				.timestamp(new Date().getTime())
				.status(status.value())
				.title(status.name())
				.detail("Field validation Error")
				.developerMessage( manvException.getLocalizedMessage())
				.fieldErrors(map)
				.build();

		return new ResponseEntity<>(validationErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException hmnrException,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder()
				.timestamp(new Date().getTime())
				.status(status.value())
				.title(status.name())
				.detail("Error parsing as date")
				.developerMessage(hmnrException.getLocalizedMessage())
				.build();
		
		
		
		
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
}
