package com.company.insuranceapp.controller;

import com.company.insuranceapp.mapper.QuestionMapper;
import com.company.insuranceapp.model.entity.Course;
import com.company.insuranceapp.model.entity.Question;
import com.company.insuranceapp.model.response.QuestionResponse;
import com.company.insuranceapp.model.response.ResponseModel;
import com.company.insuranceapp.service.abstracts.CourseService;
import com.company.insuranceapp.service.abstracts.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    private final CourseService courseService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseModel<QuestionResponse>> getById(@PathVariable Long id) {
        Question question = questionService.getById(id);
        if (question == null)
            return ResponseEntity.ok(ResponseModel.notFound("Question"));

        QuestionResponse questionResponse = QuestionMapper.toQuestionResponse(question);
        return ResponseEntity.ok(ResponseModel.success(questionResponse));
    }

    @GetMapping("/getRandomQuestion/{id}")
    public ResponseEntity<ResponseModel<QuestionResponse>> getRandomQuestion(@PathVariable("id") Long courseId) {
        Course course = courseService.getById(courseId);

        Question question = questionService.getRandomQuestionByCourse(course);
        if (question == null)
            return ResponseEntity.ok(ResponseModel.notFound("Question"));

        QuestionResponse questionResponse = QuestionMapper.toQuestionResponse(question);
        return ResponseEntity.ok(ResponseModel.success(questionResponse));
    }
}
