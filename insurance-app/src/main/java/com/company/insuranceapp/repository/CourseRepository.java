package com.company.insuranceapp.repository;

import com.company.insuranceapp.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
