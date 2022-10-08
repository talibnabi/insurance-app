package com.company.insuranceapp.service.abstracts;

import com.company.insuranceapp.model.entity.Course;

import java.util.List;

public interface CourseService {
    Course getById(Long id);

    List<Course> getAllCourses();
}
