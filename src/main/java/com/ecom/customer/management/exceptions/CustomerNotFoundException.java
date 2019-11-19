package com.ecom.customer.management.exceptions;

public class CustomerNotFoundException extends RuntimeException {

	private StringBuilder message = new StringBuilder("Customer not found with ");

	public CustomerNotFoundException(String message) {
		super();
		this.message.append(message);
	}

	public CustomerNotFoundException(String... message) {
		super();
		for (String s : message)
			this.message.append(s).append(" ");
	}

	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
		this.message.append(message);
	}

	public String getMessage() {
		return message.toString();
	}
}
