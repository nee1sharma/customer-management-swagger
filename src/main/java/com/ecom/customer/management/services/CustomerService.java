package com.ecom.customer.management.services;

import java.util.concurrent.CompletableFuture;

import com.customer.management.model.Customer;
import com.customer.management.model.CustomerList;

public interface CustomerService {

	CompletableFuture<Long> count();

	CompletableFuture<Customer> findCustomerById(int customerId);

	CompletableFuture<CustomerList> findAllCustomers();

	CompletableFuture<Integer> createCustomer(Customer customer);

	//custom jpa repo queries
	CompletableFuture<CustomerList> findCustomerByFirstName(String firstName);

	CompletableFuture<CustomerList> findCustomerByFirstNameAndLastName(String firstName, String lastName);

	CompletableFuture<Customer> findCustomerByFirstNameAndLastNameAndPhoneNumber(String firstName, String lastName, String phoneNumber);

	CompletableFuture<Customer> findCustomerByPhoneNumber(String phoneNumber);
	
	CompletableFuture<CustomerList> findAllCustomersByFirstNameOrLastName(String firstName, String lastName);
	
	CompletableFuture<CustomerList> findAllCustomersByFirstNameOrPhoneNumber(String firstName, String phoneNumber);

}
