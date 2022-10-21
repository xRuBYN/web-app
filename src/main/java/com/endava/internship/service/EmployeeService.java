package com.endava.internship.service;

import com.endava.internship.domain.dto.EmployeeRequest;
import com.endava.internship.domain.model.Employee;
import com.endava.internship.exception.DepartmentNotFoundException;
import com.endava.internship.exception.EmployeeNotFoundException;
import com.endava.internship.exception.SalaryIsLessThanOneException;

import java.util.List;

public interface EmployeeService {
    List<Employee> showAllEmployees();

    Employee findEmployeeById(Long id) throws EmployeeNotFoundException;

    Employee addEmployee(EmployeeRequest employeeRequest) throws DepartmentNotFoundException, SalaryIsLessThanOneException;

    Employee editEmployeeInformation(EmployeeRequest employeeRequest, Long id) throws EmployeeNotFoundException, SalaryIsLessThanOneException, DepartmentNotFoundException;
}
