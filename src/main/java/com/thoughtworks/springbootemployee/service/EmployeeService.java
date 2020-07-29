package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    public Employee updateEmployee(int id, Employee targetEmployee) {
        return employeeRepository.updateEmployee(id, targetEmployee);
    }

    public Employee addEmployee(Employee newEmployee) {
        return employeeRepository.insertEmployee(newEmployee);
    }

    public boolean deleteEmployeeById(int id) {
        return employeeRepository.deleteEmployeeById(id);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.getEmployeesByGender(gender);
    }
}
