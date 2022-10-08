package com.company.insuranceapp.repository;

import com.company.insuranceapp.model.entity.Course;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    UserCourse findByUserAndCourse(User user, Course course);
}
