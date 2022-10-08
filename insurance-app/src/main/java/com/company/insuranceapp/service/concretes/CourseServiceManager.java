package com.company.insuranceapp.service.concretes;

import com.company.insuranceapp.model.entity.Course;
import com.company.insuranceapp.repository.CourseRepository;
import com.company.insuranceapp.service.abstracts.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CourseServiceManager implements CourseService {
    private final  CourseRepository courseRepository;

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
