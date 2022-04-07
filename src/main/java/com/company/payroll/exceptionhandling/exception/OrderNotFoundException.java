package com.company.payroll.exceptionhandling.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Could not find the order " + id);

    }
}
