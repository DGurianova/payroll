package com.company.payroll;

public class EmployeeNotFoundException extends RuntimeException {
    EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id); //extra tidbit of Spring MVC configuration is used to render an HTTP 404:
    }
}
