package com.endava.internship.serviece.impl;


import com.endava.internship.domain.dto.DepartmentRequest;
import com.endava.internship.domain.model.Department;
import com.endava.internship.exception.DepartmentNotFoundException;
import com.endava.internship.repository.DepartmentRepository;
import com.endava.internship.service.impl.DepartmentServiceImpl;
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
class DepartmentServiceTest {

    @Mock
    DepartmentRepository repository;

    @InjectMocks
    DepartmentServiceImpl service;

    Department department;
    DepartmentRequest departmentRequest;

    Department department1;

    Department department2;
    Department department3;


    @BeforeEach
    void setUp() {
        department1 = Department.builder()
                .id(1L)
                .name("IT Department")
                .location("Europe")
                .build();
        departmentRequest = DepartmentRequest.builder()
                .name("SIS")
                .location("Africa")
                .build();

        department = Department.builder()
                .name("SIS")
                .location("Africa")
                .build();

        department2 = Department.builder()
                .id(1L)
                .name("SIS")
                .location("Africa")
                .build();

        department3 = Department.builder()
                .id(2L)
                .name("FAF")
                .location("Est Europe")
                .build();
    }

    @Test
    void testAddDepartment() throws DepartmentNotFoundException {
        when(repository.save(department)).thenReturn(department);

        assertEquals(department, service.addDepartment(departmentRequest));

    }


    @Test
    void testFindDepartmentById() throws DepartmentNotFoundException {

        when(repository.findById(1L)).thenReturn(Optional.ofNullable(department1));
        assertEquals(department1, service.findDepartmentById(1L));
    }

    @Test
    void testFindDepartmentByIdWithInvalidId() {
        when(repository.findById(4L)).thenReturn(Optional.ofNullable(null));

        DepartmentNotFoundException d = assertThrows(DepartmentNotFoundException.class, () -> service.findDepartmentById(4L));
        assertEquals("Not exist the department with id:4", d.getMessage());
    }


    @Test
    void testEditDepartment() throws DepartmentNotFoundException {
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(department1));
        when(repository.save(department2)).thenReturn(department2);

        assertEquals(department2, service.editDepartmentInformation(departmentRequest, 1L));
    }


    @Test
    void testShowAll() {
        List<Department> list = new ArrayList<>();
        list.add(department);
        list.add(department3);
        when(repository.findAll()).thenReturn(list);

        assertEquals(list, service.showAllDepartments());
    }
}
