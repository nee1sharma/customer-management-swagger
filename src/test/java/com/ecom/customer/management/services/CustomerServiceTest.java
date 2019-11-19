package com.ecom.customer.management.services;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ecom.customer.management.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Test
	public final void testCount() {
		/*when(customerRepository.count()).thenReturn(1L);
		long l = customerService.count();
		Assertions.assertThat(1).isEqualTo(l);*/
	}

	@Test
	public final void testFindCustomerById() {
		when(customerRepository.findById(122111)).thenReturn(null);
	}

	@Test
	public final void testFindAllCustomers() {

	}

	@Test
	public final void testCreateCustomer() {

	}

}
