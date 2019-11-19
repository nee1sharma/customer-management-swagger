package com.ecom.customer.management.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.customer.management.model.Customer;
import com.customer.management.model.CustomerList;
import com.ecom.customer.management.exceptions.CustomerNotFoundException;
import com.ecom.customer.management.mapper.CustomerMapper;
import com.ecom.customer.management.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class.getName());
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerMapper mapper;

	@Async
	@Override
	public CompletableFuture<Long> count() {
		LOGGER.debug("Counting customers in db");
		return CompletableFuture.completedFuture(customerRepository.count());
	}

	@Async
	@Override
	public CompletableFuture<Customer> findCustomerById(int customerId) {
		LOGGER.debug("Finding customer with id {}", customerId);
		Optional<com.ecom.customer.management.dto.Customer> optionalCustomer = customerRepository.findById(customerId);
		return CompletableFuture.completedFuture(mapper.mapCustomerDto(
				optionalCustomer.orElseThrow(() -> new CustomerNotFoundException("id ", String.valueOf(customerId)))));
	}

	@Async
	@Override
	public CompletableFuture<CustomerList> findAllCustomers() {
		LOGGER.debug("Finding all customers");
		List<com.ecom.customer.management.dto.Customer> customers = customerRepository.findAll();
		return CompletableFuture.completedFuture(mapper.mapCustomerListDto(customers));
	}

	@Async
	@Override
	public CompletableFuture<Integer> createCustomer(Customer customer) {
		LOGGER.debug("Creating new customer {}", customer);
		com.ecom.customer.management.dto.Customer customerDto = mapper.mapCustomer(customer);
		return CompletableFuture.completedFuture(customerRepository.save(customerDto).getCustomerId());
	}

	@Async
	@Override
	public CompletableFuture<CustomerList> findCustomerByFirstName(String firstName) {
		LOGGER.debug("Finding customer by first name {}", firstName);
		List<com.ecom.customer.management.dto.Customer> customerListDto = customerRepository
				.findCustomerByFirstName(firstName);
		return customerListCompletableFuture(customerListDto, "firstName", firstName);
	}

	@Async
	@Override
	public CompletableFuture<CustomerList> findCustomerByFirstNameAndLastName(String firstName, String lastName) {
		LOGGER.debug("Finding customer by name {} {}", firstName, lastName);
		List<com.ecom.customer.management.dto.Customer> customerListDto = customerRepository
				.findCustomersByFirstNameAndLastName(firstName, lastName);
		return customerListCompletableFuture(customerListDto, firstName, lastName);
	}

	@Async
	@Override
	public CompletableFuture<Customer> findCustomerByFirstNameAndLastNameAndPhoneNumber(String firstName,
			String lastName, String phoneNumber) {
		Optional<com.ecom.customer.management.dto.Customer> optionalCustomer = customerRepository
				.findCustomerByFirstNameAndLastNameAndPhoneNumber(firstName, lastName, phoneNumber);

		return CompletableFuture.completedFuture(
				mapper.mapCustomerDto(optionalCustomer.orElseThrow(() -> new CustomerNotFoundException("firstName=",
						firstName, "lastName=", lastName, "phoneNumber=", phoneNumber))));
	}

	@Async
	@Override
	public CompletableFuture<Customer> findCustomerByPhoneNumber(String phoneNumber) {
		LOGGER.debug("Finding all customers by Phone number {}", phoneNumber);
		Optional<com.ecom.customer.management.dto.Customer> optionalCustomer = customerRepository
				.findCustomerByPhoneNumber(phoneNumber);

		return CompletableFuture.completedFuture(mapper.mapCustomerDto(
				optionalCustomer.orElseThrow(() -> new CustomerNotFoundException("phoneNumber=", phoneNumber))));
	}

	@Override
	public CompletableFuture<CustomerList> findAllCustomersByFirstNameOrLastName(String firstName, String lastName) {
		LOGGER.debug("Finding all customer by first name {} or last name {}", firstName, lastName);
		List<com.ecom.customer.management.dto.Customer> customerListDto = customerRepository
				.findAllCustomersByFirstNameOrLastName(firstName, lastName);
		return customerListCompletableFuture(customerListDto, firstName, lastName);
	}

	@Override
	public CompletableFuture<CustomerList> findAllCustomersByFirstNameOrPhoneNumber(String firstName,
			String phoneNumber) {
		LOGGER.debug("Finding all customer by first name {} or phone number {}", firstName, phoneNumber);
		List<com.ecom.customer.management.dto.Customer> customerListDto = customerRepository
				.findAllCustomersByFirstNameOrPhoneNumber(firstName, phoneNumber);
		return customerListCompletableFuture(customerListDto, firstName, phoneNumber);
	}

	private CompletableFuture<CustomerList> customerListCompletableFuture(
			List<com.ecom.customer.management.dto.Customer> customerListDto, String... param) {
		if (customerListDto.isEmpty()) {
			throw new CustomerNotFoundException(param);
		}
		return CompletableFuture.completedFuture(mapper.mapCustomerListDto(customerListDto));
	}
}
