package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = generateEmployees();
    private int nextId = 5;

    private List<Employee> generateEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(0, "Xiaoming", 20, "Male", 10000));
        employees.add(new Employee(1, "Xiaohong", 19, "Female", 10000));
        employees.add(new Employee(2, "Xiaozhi", 15, "Male", 10000));
        employees.add(new Employee(3, "Xiaogang", 16, "Male", 10000));
        employees.add(new Employee(4, "Xiaoxia", 15, "Female", 10000));
        return employees;
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee getEmployeeById(int id) {
        return employees.stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);
    }

    public Employee updateEmployee(int id, Employee targetEmployee) {
        Employee employee = getEmployeeById(id);
        employee.setName(targetEmployee.getName());
        employee.setAge(targetEmployee.getAge());
        employee.setGender(targetEmployee.getGender());
        employee.setSalary(targetEmployee.getSalary());
        return employee;
    }

    public Employee insertEmployee(Employee newEmployee) {
        Employee insertedEmployee = new Employee(nextId, newEmployee.getName(), newEmployee.getAge(), newEmployee.getGender(), newEmployee.getSalary());
        nextId++;
        return insertedEmployee;
    }

    public boolean deleteEmployeeById(int id) {
        return employees.remove(getEmployeeById(id));
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employees.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    public List<Employee> getEmployeesPagination(int page, int pageSize) {
        return employees.stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }
}
