package com.ecom.customer.management.resources;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.customer.management.model.CustomerList;
import com.ecom.customer.management.services.CustomerService;
import com.ecom.customer.management.utils.ResponseUtil;
import com.ecom.customer.management.utils.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class CustomerResourceTest {

	private static final MappingJackson2HttpMessageConverter JSON_HTTP_MAPPER = new MappingJackson2HttpMessageConverter(
			ResponseUtil.getMapper());
	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerResource customerResource;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(customerResource).setMessageConverters(JSON_HTTP_MAPPER).build();
	}

	@Test
	public void testCustomersCount() throws Exception {

		when(customerService.count()).thenReturn(CompletableFuture.completedFuture(1L));

		MvcResult mvcResult = mockMvc.perform(get("/customers/count")).andExpect(request().asyncStarted()).andReturn();

		String response = mvcResult.getResponse().getContentAsString();
		Assertions.assertThat(response).isNotNull();
		Assertions.assertThat(Long.parseLong(response)).isEqualTo(1L);
	}

	@Test
	public void testGetAllCustomers_noCustomers() throws Exception {
		when(customerService.findAllCustomers()).thenReturn(
				CompletableFuture.completedFuture(new CustomerList().withCustomers(Collections.emptyList())));

		MvcResult mvcResult = mockMvc.perform(get("/customers")).andReturn();

		String response = mvcResult.getResponse().getContentAsString();

		Assertions.assertThat(response).isBlank();
	}

	@Test
	public void testGetAllCustomers_withOneCustomer() throws Exception {
		when(customerService.findAllCustomers()).thenReturn(CompletableFuture.completedFuture(
				new CustomerList().withCustomers(Collections.singletonList(TestUtil.buildCustomer()))));

		MvcResult mvcResult = mockMvc.perform(get("/customers")).andReturn();//.andExpect(request().asyncStarted())

		String response = mvcResult.getResponse().getContentAsString();

		Assertions.assertThat(response).isNotBlank();
	}
}
