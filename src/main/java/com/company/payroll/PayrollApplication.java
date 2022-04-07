package com.company.payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //This tells Spring Boot to help out, wherever possible.
// a meta-annotation that pulls in component scanning, autoconfiguration, and property support.
public class PayrollApplication {

	public static void main(String[] args) {

		SpringApplication.run(PayrollApplication.class, args);
	}

}
