package com.company.payroll.exceptionhandling.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id); //extra tidbit of Spring MVC configuration is used to render an HTTP 404:
    }
}
