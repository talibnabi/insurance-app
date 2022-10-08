package com.company.insuranceapp.controller;


import com.company.insuranceapp.mapper.AnswerMapper;
import com.company.insuranceapp.model.entity.Answer;
import com.company.insuranceapp.model.entity.Question;
import com.company.insuranceapp.model.response.AnswerResponse;
import com.company.insuranceapp.model.response.ResponseModel;
import com.company.insuranceapp.service.abstracts.AnswerService;
import com.company.insuranceapp.service.abstracts.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @GetMapping({"/{id}", "/getById/{id}"})
    public ResponseEntity<ResponseModel<AnswerResponse>> getById(@PathVariable Long id) {
        Answer answer = answerService.getById(id);
        if (answer == null)
            return ResponseEntity.ok(ResponseModel.notFound("Answer"));

        AnswerResponse answerResponse = AnswerMapper.toAnswerResponse(answer);
        return ResponseEntity.ok(ResponseModel.success(answerResponse));
    }

    @GetMapping("/getByQuestionId/{id}")
    public ResponseEntity<ResponseModel<List<AnswerResponse>>> getByQuestionId(@PathVariable Long id) {
        Question question = questionService.getById(id);
        if (question == null)
            return ResponseEntity.ok(ResponseModel.notFound("Question"));

        List<Answer> answerList = answerService.getByQuestionId(id);
        List<AnswerResponse> answerResponse = AnswerMapper.toAnswerResponse(answerList);
        return ResponseEntity.ok(ResponseModel.success(answerResponse));
    }
}
