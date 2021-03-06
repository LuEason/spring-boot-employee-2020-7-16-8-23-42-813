package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void should_return_added_employee_when_add_employee_given_a_new_employee() {
        //given
        Employee newEmployee = new Employee(-1, "Xiaohei", 15, "Female", 8000);

        //when
        Employee returnEmployee = employeeService.addEmployee(newEmployee);

        //then
        assertEquals(newEmployee.getAge(), returnEmployee.getAge());
        assertEquals(newEmployee.getGender(), returnEmployee.getGender());
        assertEquals(newEmployee.getName(), returnEmployee.getName());
        assertEquals(newEmployee.getSalary(), returnEmployee.getSalary());
    }

    @Test
    void should_return_boolean_when_delete_employee_given_id() {
        //given
        int id = 3;
        int beforeDeleteEmployeesLength = employeeService.getAllEmployees().size();

        //when
        boolean isDelete = employeeService.deleteEmployeeById(id);
        int afterDeleteEmployeesLength = employeeService.getAllEmployees().size();

        //then
        assertTrue(isDelete);
        assertEquals(beforeDeleteEmployeesLength - 1, afterDeleteEmployeesLength);
    }

    @Test
    void should_return_only_male_employee_list_when_get_employees_given_gender_is_male() {
        //given
        String gender = "Male";

        //when
        List<Employee> maleEmployees = employeeService.getEmployeesByGender(gender);

        //then
        assertEquals(0, maleEmployees.stream().filter(employee -> employee.getGender().equals("Female")).count());
        assertEquals(3, maleEmployees.stream().filter(employee -> employee.getGender().equals("Male")).count());
    }

    @Test
    void should_return_employees_in_the_specified_range_when_get_employees_given_page_2_and_page_size_2() {
        //given
        int page = 2;
        int pageSize = 2;

        //when
        List<Employee> employees = employeeService.getEmployeesPagination(page, pageSize);

        //then
        assertEquals(2, employees.get(0).getId());
        assertEquals(3, employees.get(1).getId());
    }
}
