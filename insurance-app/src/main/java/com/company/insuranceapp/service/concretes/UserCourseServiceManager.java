package com.company.insuranceapp.service.concretes;


import com.company.insuranceapp.model.entity.Course;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.entity.UserCourse;
import com.company.insuranceapp.repository.UserCourseRepository;
import com.company.insuranceapp.service.abstracts.UserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCourseServiceManager implements UserCourseService {
    private final UserCourseRepository userCourseRepository;

    @Override
    public void passCourse(UserCourse userCourse) {
        userCourseRepository.save(userCourse);
    }

    @Override
    public Boolean isCoursePassed(User user, Course course) {
        return userCourseRepository.findByUserAndCourse(user, course) != null;
    }
}
