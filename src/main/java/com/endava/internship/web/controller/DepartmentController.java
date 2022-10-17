package com.endava.internship.web.controller;


import com.endava.internship.domain.dto.DepartmentRequest;
import com.endava.internship.domain.model.Department;
import com.endava.internship.exception.DepartmentNotFoundException;
import com.endava.internship.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody DepartmentRequest departmentRequest) {
        Department department = departmentService.addDepartment(departmentRequest);

        return ResponseEntity.ok(department);
    }

    @GetMapping
    public ResponseEntity<List<Department>> showAllDepartments() {
        return ResponseEntity.ok(departmentService.showAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> showDepartmentById(@PathVariable Long id) throws DepartmentNotFoundException {
        return ResponseEntity.ok(departmentService.findDepartmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> editDepartment(@RequestBody DepartmentRequest departmentRequest, @PathVariable Long id) throws DepartmentNotFoundException {
        return ResponseEntity.ok(departmentService.editDepartmentInformation(departmentRequest, id));
    }
}
