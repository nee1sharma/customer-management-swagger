package com.ecom.customer.management.resources;

import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.management.api.CustomersApi;
import com.customer.management.model.Customer;
import com.customer.management.model.CustomerList;
import com.ecom.customer.management.services.CustomerService;

@RestController
public class CustomerResource implements CustomersApi {

	private CustomerService custSvc;
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerResource.class.getName());

	public CustomerResource(CustomerService custSvc) {
		this.custSvc = custSvc;
	}

	public CompletableFuture<ResponseEntity<Integer>> createCustomer(@Valid @RequestBody Customer customer) {
		LOGGER.debug("Customer customerId :", customer.getCustomerId());
		return custSvc.createCustomer(customer).thenApply(result -> new ResponseEntity<>(result, HttpStatus.CREATED));
	}

	@ResponseBody
	public CompletableFuture<ResponseEntity<CustomerList>> getAllCustomers() {
		LOGGER.debug("getAllCustomers {} ");
		return custSvc.findAllCustomers().thenApply(result -> new ResponseEntity<>(result, HttpStatus.OK));
	}

	public CompletableFuture<ResponseEntity<Customer>> getCustomerDetailsById(Integer customerId) {
		LOGGER.debug("get Customer Detail By Id: {}", customerId);
		return custSvc.findCustomerById(customerId).thenApply(result -> new ResponseEntity<>(result, HttpStatus.FOUND));
	}

	@GetMapping("/customers/count")
	public CompletableFuture<ResponseEntity<Long>> countCustomers() {
		LOGGER.debug("Total number of customers found: ");
		return custSvc.count().thenApply(result -> new ResponseEntity<>(result, HttpStatus.OK));
	}

	@GetMapping("/customers/{firstName}")
	public CompletableFuture<ResponseEntity<CustomerList>> findCustomerByFirstName(
			@PathVariable(value = "firstName") String firstName) {
		LOGGER.debug("Find customers by first Name : {}", firstName);
		return custSvc.findCustomerByFirstName(firstName)
				.thenApply(result -> new ResponseEntity<>(result, HttpStatus.OK));
	}

	@GetMapping("/customers/{firstName}/{lastName}")
	public CompletableFuture<ResponseEntity<CustomerList>> findCustomerByFirstNameAndLastName(
			@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
		LOGGER.debug("Find customers by Name : {} {}", firstName, lastName);
		return custSvc.findCustomerByFirstNameAndLastName(firstName, lastName)
				.thenApply(result -> new ResponseEntity<>(result, HttpStatus.OK));
	}

	@GetMapping("/customerss")
	public CompletableFuture<ResponseEntity<Customer>> findCustomerByFirstNameAndLastNameAndPhoneNumber(
			@RequestParam("fn") String firstName, @RequestParam("ln") String lastName,
			@RequestParam("pn") String phoneNumber) {
		LOGGER.debug("Find customer by name {} {} and phone number {}", firstName, lastName, phoneNumber);
		return custSvc.findCustomerByFirstNameAndLastNameAndPhoneNumber(firstName, lastName, phoneNumber)
				.thenApply(result -> new ResponseEntity<>(result, HttpStatus.OK));
	}

	@GetMapping("/customers/{firstName}/ln/{lastName}")
	public CompletableFuture<ResponseEntity<CustomerList>> findCustomerByFirstNameOrLastName(
			@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
		LOGGER.debug("Find customers by Name : {} {}", firstName, lastName);
		return custSvc.findAllCustomersByFirstNameOrLastName(firstName, lastName)
				.thenApply(result -> new ResponseEntity<>(result, HttpStatus.OK));
	}

	@GetMapping("/customers/{firstName}/pn/{phoneNumber}")
	public CompletableFuture<ResponseEntity<CustomerList>> findCustomerByFirstNameOrPhoneNumber(
			@PathVariable("firstName") String firstName, @PathVariable("phoneNumber") String phoneNumber) {
		LOGGER.debug("Find customers by Name : {} {}", firstName, phoneNumber);
		return custSvc.findAllCustomersByFirstNameOrPhoneNumber(firstName, phoneNumber)
				.thenApply(result -> new ResponseEntity<>(result, HttpStatus.OK));
	}
}
