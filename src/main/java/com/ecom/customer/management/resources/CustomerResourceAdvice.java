package com.ecom.customer.management.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecom.customer.management.exceptions.CustomerNotFoundException;

@RestControllerAdvice
public class CustomerResourceAdvice extends ResponseEntityExceptionHandler{

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerResourceAdvice.class.getSimpleName());

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
		LOGGER.debug("handleCustomerNotFoundException {}", request.getSessionId());
		System.out.println("handleCustomerNotFoundException "+ request.getSessionId());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		//return handleExceptionInternal(ex, "----", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
/*	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleExceptions(Exception ex, WebRequest request) {
		LOGGER.debug("handleExceptions {}", request.getSessionId());
		System.out.println("handleExceptions "+ request.getSessionId());
		//return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		return handleExceptionInternal(ex, "----", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}*/
}
