package com.company.payroll.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity //is a JPA annotation to make this object ready for storage in a JPA-based data store.
public class Employee {
    private @Id
    @GeneratedValue
    Long id; //is marked with more JPA annotations to indicate it’s the primary key and automatically populated by the JPA provider
    private String firstName; //is attribute of our Employee
    private String lastName; //is attribute of our Employee
    private String role;//is attribute of our Employee

    public Employee() {
    }

    public Employee(String firstName, String lastName, String role) { //a custom constructor is created when we need to create a new instance, but don’t yet have an id.
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public Long getId() {

        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.firstName, employee.firstName) && Objects.equals(this.lastName, employee.lastName) && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, role);
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + this.id +
                ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName + '\'' +
                ", role='" + this.role + '\'' +
                '}';
    }
}
