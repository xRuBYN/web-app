package com.endava.internship.service;

import com.endava.internship.domain.dto.DepartmentRequest;
import com.endava.internship.domain.model.Department;
import com.endava.internship.exception.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    List<Department> showAllDepartments();

    Department findDepartmentById(Long id) throws DepartmentNotFoundException;

    Department addDepartment(DepartmentRequest departmentRequest);

    Department editDepartmentInformation(DepartmentRequest departmentRequest, Long id) throws DepartmentNotFoundException;
}
