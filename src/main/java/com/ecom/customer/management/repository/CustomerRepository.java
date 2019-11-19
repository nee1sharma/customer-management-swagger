package com.ecom.customer.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.customer.management.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);
	Optional<Customer> findCustomerByFirstNameAndLastNameAndPhoneNumber(String firstName, String lastName, String phoneNumber);

	List<Customer> findCustomerByFirstName(String firstName);
	List<Customer> findCustomersByFirstNameAndLastName(String firstName, String lastName);

	List<Customer> findAllCustomersByFirstNameOrLastName(String firstName, String lastName);
	List<Customer> findAllCustomersByFirstNameOrPhoneNumber(String firstName, String phoneNumber);
}