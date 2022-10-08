package com.company.insuranceapp.service.abstracts;

import com.company.insuranceapp.model.entity.Course;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.entity.UserCourse;

public interface UserCourseService {

    void passCourse(UserCourse userCourse);

    Boolean isCoursePassed(User user, Course course);
}
