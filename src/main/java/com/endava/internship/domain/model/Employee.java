package com.endava.internship.domain.model;

import com.endava.internship.domain.dto.EmployeeRequest;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Check(constraints = "salary >= 1.0")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "salary")
    private Double salary;

    @JoinColumn(name = "department_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;


    public EmployeeRequest employeeToEmployeeRequest() {
        return EmployeeRequest.builder()
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .email(this.getEmail())
                .phoneNumber(this.getPhoneNumber())
                .salary(this.getSalary())
                .departmentId(this.getDepartment().getId())
                .build();
    }


}
