package com.company.payroll.repository;

import com.company.payroll.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> { // specifying the domain type as Employee and the id type as Long.

}
