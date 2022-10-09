package com.company.insuranceapp.controller;

import com.company.insuranceapp.mapper.QuestionMapper;
import com.company.insuranceapp.model.entity.Answer;
import com.company.insuranceapp.model.entity.Course;
import com.company.insuranceapp.model.entity.Question;
import com.company.insuranceapp.model.response.QuestionResponse;
import com.company.insuranceapp.model.response.ResponseModel;
import com.company.insuranceapp.service.abstracts.AnswerService;
import com.company.insuranceapp.service.abstracts.CourseService;
import com.company.insuranceapp.service.abstracts.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final CourseService courseService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseModel<QuestionResponse>> getById(@PathVariable Long id) {
        Question question = questionService.getById(id);
        if (question == null)
            return ResponseEntity.ok(ResponseModel.notFound("Question"));

        QuestionResponse questionResponse = QuestionMapper.toQuestionResponse(question);
        return ResponseEntity.ok(ResponseModel.success(questionResponse));
    }

    @GetMapping("/getAllQuestion")
    public ResponseEntity<ResponseModel<List<QuestionResponse>>> getAllQuestion() {
        List<Question> questions = questionService.getAllQuestion();
        if (questions.isEmpty())
            return ResponseEntity.ok(ResponseModel.notFound("Question"));
        List<QuestionResponse> questionResponses = new ArrayList<>();

        questions.forEach(question ->
        {
            QuestionResponse questionResponse = QuestionMapper.toQuestionResponse(question);
            List<Answer> answers = answerService.getByQuestionId(question.getId());
            List<String> answerList = new ArrayList<>();
            answers.forEach(answer -> answerList.add(answer.getAnswer()));

            questionResponse.setRightAnswerIndex((long) answerList.indexOf(question.getRightAnswer().getAnswer()));
            questionResponse.setAnswers(answerList);
            questionResponses.add(questionResponse);
        });

        return ResponseEntity.ok(ResponseModel.success(questionResponses));
    }

    @GetMapping("/getRandomQuestion/{id}")
    public ResponseEntity<ResponseModel<QuestionResponse>> getRandomQuestion(
            @PathVariable(value = "id", required = false) Long courseId) {
        Course course;
        if (courseId != null )
            course = courseService.getById(courseId);
        else
            course = null;

        Question question = questionService.getRandomQuestionByCourse(course);
        if (question == null)
            return ResponseEntity.ok(ResponseModel.notFound("Question"));

        QuestionResponse questionResponse = QuestionMapper.toQuestionResponse(question);
        return ResponseEntity.ok(ResponseModel.success(questionResponse));
    }

}
