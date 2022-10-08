package com.company.insuranceapp.model.request;

import lombok.Data;

@Data
public class UserCourseRequest {
    private Long userId;
    private Long courseId;
}
