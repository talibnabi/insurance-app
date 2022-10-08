package com.company.insuranceapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseResponse {
    private Long id;
    private String name;
    private String description;
    private Long coverId;
    private Long videoId;
}
