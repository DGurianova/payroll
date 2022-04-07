package com.company.payroll;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> { // specifying the domain type as Employee and the id type as Long.

}
