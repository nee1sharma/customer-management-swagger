package com.ecom.customer.management.mapper;

import com.customer.management.model.Customer;
import com.customer.management.model.CustomerList;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Customer mapCustomerDto(com.ecom.customer.management.dto.Customer customerDto) {
        return new Customer().withCustomerId(customerDto.getCustomerId())
        		.withCreationDate(customerDto.getCreationDate().toString())
                .withEmailId(customerDto.getEmailId())
                .withFirstName(customerDto.getFirstName())
                .withLastName(customerDto.getLastName())
                .withPhoneNumber(customerDto.getPhoneNumber());
        //return modelMapper.map(customerDto, Customer.class);
    }

    public com.ecom.customer.management.dto.Customer mapCustomer(Customer customer) {
        return modelMapper.map(customer, com.ecom.customer.management.dto.Customer.class);
    }

    public CustomerList mapCustomerListDto(List<com.ecom.customer.management.dto.Customer> customerListDto) {

        List<Customer> customers = customerListDto.stream().map(dto -> mapCustomerDto(dto))
                .collect(Collectors.toList());

        return new CustomerList().withCustomers(customers);
    }
}
