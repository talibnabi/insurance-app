package com.company.insuranceapp.controller;


import com.company.insuranceapp.model.entity.Course;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.entity.UserCourse;
import com.company.insuranceapp.model.request.UserCourseRequest;
import com.company.insuranceapp.model.response.ResponseModel;
import com.company.insuranceapp.service.abstracts.CourseService;
import com.company.insuranceapp.service.abstracts.UserCourseService;
import com.company.insuranceapp.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userCourse")
public class UserCourseController {
    private final UserCourseService userCourseService;
    private final UserService userService;
    private final CourseService courseService;

    @PostMapping("/passCourse")
    public ResponseEntity<ResponseModel> passQuestion(@RequestBody UserCourseRequest userCourseRequest) {
        User user = userService.findByUserId(userCourseRequest.getUserId());
        if (user == null)
            return ResponseEntity.ok(ResponseModel.notFound("User"));

        Course course = courseService.getById(userCourseRequest.getCourseId());
        if (course == null)
            return ResponseEntity.ok(ResponseModel.notFound("Course"));

        if (userCourseService.isCoursePassed(user, course))
            return ResponseEntity.ok(ResponseModel.error(null, "User already passed this course"));

        UserCourse userCourse = new UserCourse(user, course);
        userCourseService.passCourse(userCourse);
        return ResponseEntity.ok(ResponseModel.success(null));
    }

    @GetMapping("/isCoursePassed")
    public ResponseEntity<ResponseModel<Boolean>> isCoursePassed(@RequestBody UserCourseRequest userCourseRequest) {
        User user = userService.findByUserId(userCourseRequest.getUserId());
        if (user == null)
            return ResponseEntity.ok(ResponseModel.notFound("User"));

        Course course = courseService.getById(userCourseRequest.getCourseId());
        if (course == null)
            return ResponseEntity.ok(ResponseModel.notFound("Course"));

        Boolean is = userCourseService.isCoursePassed(user, course);
        return ResponseEntity.ok(ResponseModel.success(is));
    }
}
