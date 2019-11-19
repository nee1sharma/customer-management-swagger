package com.ecom.customer.management.mapper;

import java.util.Collections;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.customer.management.model.Customer;
import com.customer.management.model.CustomerList;
import com.ecom.customer.management.utils.ResponseUtil;
import com.ecom.customer.management.utils.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(MockitoJUnitRunner.class)
public class CustomerMapperTest {

	@InjectMocks
	private CustomerMapper mapper;// = new CustomerMapper();
	private final Customer customerMock = TestUtil.buildCustomer();
	private final com.ecom.customer.management.dto.Customer customerDtoMock = TestUtil.buildCustomerDto();

	@Test
	public final void testMapCustomerDto() throws Exception {

		com.ecom.customer.management.dto.Customer customerDto = mapper.mapCustomer(customerMock);
		System.out.println(ResponseUtil.readValueAsString(customerDto));
		assertCustomer(customerDto);
	}

	@Test
	public final void testMapCustomer() throws JsonProcessingException {
		Customer customer = mapper.mapCustomerDto(customerDtoMock);
		System.out.println(ResponseUtil.readValueAsString(customer));
		assertCustomerDto(customer);
	}

	@Test
	public final void testMapCustomerListDto() throws JsonProcessingException {
		CustomerList customerList = mapper.mapCustomerListDto(Collections.singletonList(customerDtoMock));
		System.out.println(ResponseUtil.readValueAsString(customerList));

		Assertions.assertThat(customerList).isNotNull();
		Assertions.assertThat(customerList.getCustomers().isEmpty()).isFalse();
		customerList.getCustomers().forEach(customer -> assertCustomerDto(customer));
	}

	private void assertCustomer(com.ecom.customer.management.dto.Customer customerDto) {
		Assertions.assertThat(customerDto).isNotNull();
		Assertions.assertThat(customerMock.getCustomerId()).isEqualTo(customerDto.getCustomerId());
		Assertions.assertThat(customerMock.getCreationDate()).isEqualTo(customerDto.getCreationDate());
		Assertions.assertThat(customerMock.getEmailId()).isEqualTo(customerDto.getEmailId());
		Assertions.assertThat(customerMock.getFirstName()).isEqualTo(customerDto.getFirstName());
		Assertions.assertThat(customerMock.getLastName()).isEqualTo(customerDto.getLastName());
		Assertions.assertThat(customerMock.getPhoneNumber()).isEqualTo(customerDto.getPhoneNumber());
	}

	private void assertCustomerDto(Customer customer) {
		Assertions.assertThat(customer).isNotNull();
		Assertions.assertThat(customer.getCustomerId()).isEqualTo(customerDtoMock.getCustomerId());
		Assertions.assertThat(customer.getCreationDate()).isEqualTo(customerDtoMock.getCreationDate());
		Assertions.assertThat(customer.getEmailId()).isEqualTo(customerDtoMock.getEmailId());
		Assertions.assertThat(customer.getFirstName()).isEqualTo(customerDtoMock.getFirstName());
		Assertions.assertThat(customer.getLastName()).isEqualTo(customerDtoMock.getLastName());
		Assertions.assertThat(customer.getPhoneNumber()).isEqualTo(customerDtoMock.getPhoneNumber());
	}
}
