package com.company.insuranceapp.controller;

import com.company.insuranceapp.model.entity.Question;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.entity.UserQuestion;
import com.company.insuranceapp.model.request.UserQuestionRequest;
import com.company.insuranceapp.model.response.ResponseModel;
import com.company.insuranceapp.service.abstracts.QuestionService;
import com.company.insuranceapp.service.abstracts.UserQuestionService;
import com.company.insuranceapp.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/userQuestion")
@RequiredArgsConstructor
public class UserQuestionController {
    private final UserQuestionService userQuestionService;
    private final UserService userService;
    private final QuestionService questionService;

    @PostMapping("/pass")
    public ResponseEntity<ResponseModel> passQuestion(@RequestBody UserQuestionRequest userQuestionRequest)
    {
        User user = userService.findByUserId(userQuestionRequest.getUserId());
        if (user == null)
            return ResponseEntity.ok(ResponseModel.notFound("User"));

        Question question = questionService.getById(userQuestionRequest.getQuestionId());
        if (question == null)
            return ResponseEntity.ok(ResponseModel.notFound("Question"));

        if (userQuestionService.isQuestionPassed(user, question))
            return ResponseEntity.ok(ResponseModel.error(null, "User already passed this question"));

        UserQuestion userQuestion = new UserQuestion(user, question, userQuestionRequest.getPoint());
        userQuestionService.passQuestion(userQuestion);
        return ResponseEntity.ok(ResponseModel.success(null));
    }

    @GetMapping("/getUserPoint/{id}")
    public ResponseEntity<ResponseModel<Long>> getUserPoint (@PathVariable Long id)
    {
        User user = userService.findByUserId(id);
        if (user == null)
            return ResponseEntity.ok(ResponseModel.notFound("User"));

        Long point = userQuestionService.getUserPoint(user);
        return ResponseEntity.ok(ResponseModel.success(point));
    }
}
