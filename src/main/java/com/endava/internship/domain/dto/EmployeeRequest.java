package com.endava.internship.domain.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeRequest {


    private String lastName;

    private String firstName;

    private double salary;

    private String email;

    private String phoneNumber;

    private Long departmentId;

}
