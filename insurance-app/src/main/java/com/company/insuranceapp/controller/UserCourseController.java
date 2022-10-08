package com.company.insuranceapp.controller;


import com.company.insuranceapp.service.abstracts.CourseService;
import com.company.insuranceapp.service.abstracts.UserCourseService;
import com.company.insuranceapp.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/userCourse")
@RequiredArgsConstructor
public class UserCourseController {
    private final UserCourseService userCourseService;
    private final UserService userService;
    private final CourseService courseService;

}
