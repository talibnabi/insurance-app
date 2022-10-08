package com.company.insuranceapp.mapper;

import com.company.insuranceapp.model.entity.Course;
import com.company.insuranceapp.model.response.CourseResponse;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper {
    public static CourseResponse toCourseResponse(Course course) {
        return new CourseResponse(course.getId(), course.getName(),
                course.getDescription(), course.getStorage());
    }
    public static List<CourseResponse> toCourseResponse(List<Course> courses) {
        List<CourseResponse> courseResponses = new ArrayList<>();
        courses.forEach(course -> courseResponses.add(toCourseResponse(course)));
        return courseResponses;
    }
}
