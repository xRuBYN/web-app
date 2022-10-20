package com.endava.internship.serviece.impl;

import com.endava.internship.domain.dto.EmployeeRequest;
import com.endava.internship.domain.model.Department;
import com.endava.internship.domain.model.Employee;
import com.endava.internship.exception.DepartmentNotFoundException;
import com.endava.internship.exception.EmployeeNotFoundException;
import com.endava.internship.exception.SalaryIsLessThanOneException;
import com.endava.internship.repository.DepartmentRepository;
import com.endava.internship.repository.EmployeeRepository;
import com.endava.internship.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    EmployeeServiceImpl service;

    Employee employee;

    Employee employee1;

    Employee employee2;

    Department department;

    Department department1;

    EmployeeRequest employeeRequest;

    EmployeeRequest employeeSalary;

    @BeforeEach
    void setUp() {

        department1 = Department.builder()
                .id(1L)
                .name("IT Department")
                .location("Europe")
                .build();

        department = Department.builder()
                .id(2L)
                .name("SIS")
                .location("Africa")
                .build();

        employee = Employee.builder()
                .id(1L)
                .firstName("Ursu")
                .lastName("Rubin")
                .email("rubin.ursu@mail.ru")
                .department(department1)
                .phoneNumber("777")
                .salary(30D)
                .build();

        employee1 = Employee.builder()
                .id(1L)
                .firstName("Ion")
                .lastName("Dorin")
                .email("dorin.ion@mail.ru")
                .department(department)
                .phoneNumber("666")
                .salary(20D)
                .build();

        employeeRequest = EmployeeRequest.builder()
                .firstName("Ursu")
                .lastName("Rubin")
                .email("rubin.ursu@mail.ru")
                .phoneNumber("777")
                .salary(30D)
                .departmentId(2L)
                .build();

        employee2 = Employee.builder()
                .firstName("Ursu")
                .lastName("Rubin")
                .email("rubin.ursu@mail.ru")
                .department(department)
                .phoneNumber("777")
                .salary(30D)
                .build();

        employeeSalary = EmployeeRequest.builder()
                .firstName("Ursu")
                .lastName("Rubin")
                .email("rubin.ursu@mail.ru")
                .phoneNumber("777")
                .salary(-30D)
                .departmentId(2L)
                .build();
    }

    @Test
    void testShowAllEmployees() {
        List<Employee> list = new ArrayList<>();

        list.add(employee);
        list.add(employee1);

        when(employeeRepository.findAll()).thenReturn(list);

        assertEquals(list,service.showAllEmployees());
    }

    @Test
    void testAddEmployee() throws DepartmentNotFoundException, SalaryIsLessThanOneException {
        when(departmentRepository.findById(2L)).thenReturn(Optional.ofNullable(department));
        when(employeeRepository.save(employee2)).thenReturn(employee2);

        assertEquals(employee2,service.addEmployee(employeeRequest));
    }

    @Test
    void testAddEmployeeWithInvalidIdDepartment() {
        when(departmentRepository.findById(2L)).thenReturn(Optional.ofNullable(null));

        DepartmentNotFoundException departmentNotFoundException =
                assertThrows(DepartmentNotFoundException.class,() ->service.addEmployee(employeeRequest));

        assertEquals("Department with id 2 not exist.",departmentNotFoundException.getMessage());
    }

    @Test
    void testAddEmployeeWithInvalidSalary() {
        when(departmentRepository.findById(2L)).thenReturn(Optional.ofNullable(department));

        SalaryIsLessThanOneException salaryIsLessThanOneException =
                assertThrows(SalaryIsLessThanOneException.class, () -> service.addEmployee(employeeSalary));

        assertEquals("Salary is less than 1. You introduce:-30.0",salaryIsLessThanOneException.getMessage());
    }

    @Test
    void testFindEmployeeById() throws EmployeeNotFoundException {
        when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employee));

        assertEquals(employee,service.findEmployeeById(1L));
    }

    @Test
    void testFindEmployeeByIdWithInvalidId() {
        when(employeeRepository.findById(3L)).thenReturn(Optional.ofNullable(null));

        EmployeeNotFoundException exception =
                assertThrows(EmployeeNotFoundException.class, () -> service.findEmployeeById(3L));

        assertEquals("Employee with id 3 not exist.",exception.getMessage());

    }

    @Test
    void testEditEmployeeInformation() throws EmployeeNotFoundException, SalaryIsLessThanOneException, DepartmentNotFoundException {
        when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employee1));
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(departmentRepository.findById(employeeRequest.getDepartmentId())).thenReturn(Optional.ofNullable(department1));
        assertEquals(employee,service.editEmployeeInformation(employeeRequest,1L));

    }


}
