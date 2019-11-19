package com.ecom.customer.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableAsync
public class CustomerManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerManagementApplication.class, args);
	}

	/*@Bean("defaultExecutor")
	public Executor getAsyncExecutor() {
		return new ThreadPoolTaskExecutor();
	}*/
	/*
	 * @Bean public ModelMapper getModelMapper() { return new ModelMapper(); }
	 */
}
