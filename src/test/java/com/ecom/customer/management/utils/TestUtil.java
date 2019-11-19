package com.ecom.customer.management.utils;

import java.io.IOException;
import java.time.LocalDate;

import com.customer.management.model.Customer;

public final class TestUtil {
	TestUtil(){}
	public static Customer buildCustomer() {
		return new Customer().withCreationDate(LocalDate.now().toString()).withCustomerId(122111)
				.withEmailId("abc@gv.co").withFirstName("Brian").withLastName("Clove").withPhoneNumber("4567898123");
	}

    public static com.ecom.customer.management.dto.Customer buildCustomerDto() {
        try {
            return ResponseUtil.readJson(com.ecom.customer.management.dto.Customer.class, "dto");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
