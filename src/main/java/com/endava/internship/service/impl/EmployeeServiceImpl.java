package com.endava.internship.service.impl;

import com.endava.internship.domain.dto.EmployeeRequest;
import com.endava.internship.domain.model.Department;
import com.endava.internship.domain.model.Employee;
import com.endava.internship.exception.DepartmentNotFoundException;
import com.endava.internship.exception.EmployeeNotFoundException;
import com.endava.internship.exception.SalaryIsLessThanOneException;
import com.endava.internship.repository.DepartmentRepository;
import com.endava.internship.repository.EmployeeRepository;
import com.endava.internship.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    public static final double ENTRY_SALARY = 1.0d;
    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Employee> showAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not exist."));
    }

    @Override
    public Employee addEmployee(EmployeeRequest employeeRequest) throws DepartmentNotFoundException, SalaryIsLessThanOneException {

        long id = employeeRequest.getDepartmentId();

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with id " + id + " not exist."));


        if (employeeRequest.getSalary() < ENTRY_SALARY) {
            throw new SalaryIsLessThanOneException("Salary is less than 1. You introduce:" + employeeRequest.getSalary());
        }
        Employee employee = Employee.builder()
                .lastName(employeeRequest.getLastName())
                .firstName(employeeRequest.getFirstName())
                .department(department)
                .email(employeeRequest.getEmail())
                .salary(employeeRequest.getSalary())
                .phoneNumber(employeeRequest.getPhoneNumber())
                .build();

        return employeeRepository.save(employee);
    }

    @Override
    public Employee editEmployeeInformation(EmployeeRequest employeeRequest, Long id) throws EmployeeNotFoundException, SalaryIsLessThanOneException, DepartmentNotFoundException {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("You can't edit an existent employee"));
        if (employeeRequest.getLastName() != null) {
            employee.setLastName(employeeRequest.getLastName());
        }
        if (employeeRequest.getFirstName() != null) {
            employee.setFirstName(employeeRequest.getFirstName());
        }
        if (employeeRequest.getEmail() != null) {
            employee.setEmail(employeeRequest.getEmail());
        }
        if (employeeRequest.getSalary() >= ENTRY_SALARY) {
            employee.setSalary(employeeRequest.getSalary());
        }
        if(employeeRequest.getDepartmentId() != null) {
            employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        }
        if (employeeRequest.getDepartmentId() != null && departmentRepository.findById(employeeRequest.getDepartmentId()).orElse(null) != null) {
            employee.setDepartment(departmentRepository.findById(employeeRequest.getDepartmentId()).get());
        }
        return employeeRepository.save(employee);
    }
}
