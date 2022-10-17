package com.endava.internship.service.impl;

import com.endava.internship.domain.dto.DepartmentRequest;
import com.endava.internship.domain.model.Department;
import com.endava.internship.exception.DepartmentNotFoundException;
import com.endava.internship.repository.DepartmentRepository;
import com.endava.internship.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> showAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findDepartmentById(Long id) throws DepartmentNotFoundException {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Not exist the department with id:" + id));
    }

    @Override
    public Department addDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .name(departmentRequest.getName())
                .location(departmentRequest.getLocation())
                .build();
        return departmentRepository.save(department);
    }

    @Override
    public Department editDepartmentInformation(DepartmentRequest departmentRequest, Long id) throws DepartmentNotFoundException {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Not exist the department with id:" + id));
        if (departmentRequest.getName() != null) {
            department.setName(departmentRequest.getName());
        }
        if (departmentRequest.getLocation() != null) {
            department.setLocation(departmentRequest.getLocation());
        }
        return departmentRepository.save(department);
    }
}
