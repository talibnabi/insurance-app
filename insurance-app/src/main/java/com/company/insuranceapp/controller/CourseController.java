package com.company.insuranceapp.controller;


import com.company.insuranceapp.mapper.CourseMapper;
import com.company.insuranceapp.model.entity.Course;
import com.company.insuranceapp.model.response.CourseResponse;
import com.company.insuranceapp.model.response.ResponseModel;
import com.company.insuranceapp.service.abstracts.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseServiceManager;

    @GetMapping({"/{id}", "/getByCourseId/{id}"})
    public ResponseEntity<ResponseModel<CourseResponse>> getById(@PathVariable Long id) {
        Course course = courseServiceManager.getById(id);
        if (course == null)
            return ResponseEntity.ok(ResponseModel.notFound("Course"));

        CourseResponse courseResponse = CourseMapper.toCourseResponse(course);
        return ResponseEntity.ok(ResponseModel.success(courseResponse));
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<ResponseModel<List<CourseResponse>>> getAllCourses() {

        List<Course> courses = courseServiceManager.getAllCourses();
        if (courses.isEmpty())
            return ResponseEntity.ok(ResponseModel.notFound("Course"));
        List<CourseResponse> courseResponse = CourseMapper.toCourseResponse(courses);
        return ResponseEntity.ok(ResponseModel.success(courseResponse));
    }


}
