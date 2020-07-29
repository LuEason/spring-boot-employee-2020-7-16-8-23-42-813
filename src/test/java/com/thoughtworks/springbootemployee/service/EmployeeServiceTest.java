package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    void initialTest() {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void should_return_all_employees_when_get_all_employees() {
        //given

        //when
        List<Employee> employees = employeeService.getAllEmployees();

        //then
        assertEquals(5, employees.size());
    }

    @Test
    void should_return_correspond_employee_when_get_employee_by_id_given_id() {
        //given
        int id = 1;

        //when
        Employee employee = employeeService.getEmployeeById(id);

        //then
        assertEquals(id, employee.getId());
    }

    @Test
    void should_return_updated_employee_when_update_employee_give_employee_id_and_target_employee() {
        //given
        int id = 1;
        Employee targetEmployee = new Employee(1, "Xiaohong1", 20, "Male", 9000);

        //when
        Employee updatedEmployee = employeeService.updateEmployee(id, targetEmployee);

        //then
        assertEquals(targetEmployee.getId(), updatedEmployee.getId());
        assertEquals(targetEmployee.getAge(), updatedEmployee.getAge());
        assertEquals(targetEmployee.getGender(), updatedEmployee.getGender());
        assertEquals(targetEmployee.getName(), updatedEmployee.getName());
        assertEquals(targetEmployee.getSalary(), updatedEmployee.getSalary());
    }
}
