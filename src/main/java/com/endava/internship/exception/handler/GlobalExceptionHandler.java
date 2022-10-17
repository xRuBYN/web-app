package com.endava.internship.exception.handler;

import com.endava.internship.domain.dto.ErrorResponse;
import com.endava.internship.exception.DepartmentNotFoundException;
import com.endava.internship.exception.EmployeeNotFoundException;
import com.endava.internship.exception.SalaryIsLessThanOneException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
    public static final String DEPARTMENT_NOT_FOUND = "Department not found";
    public static final String SALARY_IS_LESS = "Salary is less";

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(HttpServletRequest httpServletRequest, EmployeeNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), httpServletRequest.getServletPath(), EMPLOYEE_NOT_FOUND));
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentNotFoundException(HttpServletRequest httpServletRequest, DepartmentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), httpServletRequest.getServletPath(), DEPARTMENT_NOT_FOUND));
    }


    @ExceptionHandler(SalaryIsLessThanOneException.class)
    public ResponseEntity<ErrorResponse> handleSalaryException(HttpServletRequest httpServletRequest, SalaryIsLessThanOneException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), httpServletRequest.getServletPath(), SALARY_IS_LESS));
    }

}