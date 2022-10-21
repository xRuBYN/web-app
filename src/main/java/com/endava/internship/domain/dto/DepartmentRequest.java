package com.endava.internship.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentRequest {

    private String name;

    private String location;
}
