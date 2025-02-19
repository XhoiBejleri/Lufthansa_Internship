package com.lhind.internship.jdbc.main;

import com.lhind.internship.jdbc.model.Employee;
import com.lhind.internship.jdbc.repository.EmployeeRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeRepository employeeRepository = new EmployeeRepository();

        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            System.out.println("No employees found in the database.");
        } else {
            System.out.println("Employees in the database:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }
}
