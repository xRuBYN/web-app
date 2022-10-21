package com.endava.internship.web.controller;

import com.endava.internship.domain.dto.EmployeeRequest;
import com.endava.internship.domain.model.Employee;
import com.endava.internship.exception.DepartmentNotFoundException;
import com.endava.internship.exception.EmployeeNotFoundException;
import com.endava.internship.exception.SalaryIsLessThanOneException;
import com.endava.internship.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> showAllEmployees() {
        List<Employee> list = employeeService.showAllEmployees();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeRequest employeeRequest) throws DepartmentNotFoundException, SalaryIsLessThanOneException {
        Employee employee = employeeService.addEmployee(employeeRequest);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getAnEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException {
        Employee employee = employeeService.findEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> editEmployeeById(@RequestBody EmployeeRequest employeeRequest, @PathVariable Long id) throws EmployeeNotFoundException, SalaryIsLessThanOneException, DepartmentNotFoundException {
        Employee employee = employeeService.editEmployeeInformation(employeeRequest, id);
        return ResponseEntity.ok(employee);
    }
}
