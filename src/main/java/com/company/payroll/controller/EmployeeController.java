package com.company.payroll.controller;


import com.company.payroll.assembler.EmployeeModelAssembler;
import com.company.payroll.exceptionhandling.exception.EmployeeNotFoundException;
import com.company.payroll.repository.EmployeeRepository;
import com.company.payroll.domain.Employee;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController //indicates that the data returned by each method will be written straight into the response body instead of rendering a template.
public class EmployeeController {
    private final EmployeeRepository repository; // is injected by constructor into the controller.
    private final EmployeeModelAssembler assembler;

    public EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    @PostMapping("/employees")
//POST
        //create new
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {
        EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));
        return ResponseEntity//is used to create an HTTP 201 Created status message This type of response typically includes a Location response header, and we use the URI derived from the model’s self-related link.
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel); // return the model-based version of the saved object.
    }

    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all() { //another Spring HATEOAS container
        //it’s aimed at encapsulating collections (it should encapsulate collections of employee resources.) of resources—instead of a single resource entity, like EntityModel<> from earlier.
        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(employees,
                linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }


    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable Long id) { // single-item employee method
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return assembler.toModel(employee);
    }


    @PutMapping("/employees/{id}")
//PUT //replace employee by id
    public ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        Employee updatedEmployee = repository.findById(id) //
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    throw new EmployeeNotFoundException(id);
                });
        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//This method returns a Link which must be turned into a URI with the toUri method.
                .body(entityModel);
    }

    @DeleteMapping("/employees/{id}")
        //DELETE
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build(); //This returns an HTTP 204 No Content response.
    }
}
