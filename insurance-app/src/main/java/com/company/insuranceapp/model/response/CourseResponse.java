package com.company.insuranceapp.model.response;

import com.company.insuranceapp.model.entity.Storage;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseResponse {
    private Long id;
    private String name;
    private String description;
    private Storage storage;
}
