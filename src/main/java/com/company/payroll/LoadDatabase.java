package com.company.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {//Spring Boot will run ALL CommandLineRunner beans once the application context is loaded.
        // The runner will request a copy of EmployeeRepository
        return args -> {
            employeeRepository.save(new Employee("Harry", "Potter", "student"));
            employeeRepository.save(new Employee("Padma", "Patil", "student"));
            employeeRepository.save(new Employee("Ronald", "Weasley", "student"));
            employeeRepository.save(new Employee("Hermine", "Granger", "student"));
            employeeRepository.save(new Employee("Neville", "Longbottom", "student"));
            employeeRepository.save(new Employee("Luna", "Lovegood", "student"));

            employeeRepository.findAll().forEach(employee ->
                    log.info("Preloaded " + employee));


            orderRepository.save(new Order("Griffindor", Status.COMPLETED));
            orderRepository.save(new Order("Ravenclaw", Status.COMPLETED));
            orderRepository.save(new Order("Griffindor", Status.COMPLETED));
            orderRepository.save(new Order("Griffindor", Status.COMPLETED));
            orderRepository.save(new Order("Griffindor", Status.COMPLETED));
            orderRepository.save(new Order("Ravenclaw", Status.COMPLETED));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
        };
    }
}
