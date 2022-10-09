package com.company.insuranceapp.mapper;

import com.company.insuranceapp.model.entity.Answer;
import com.company.insuranceapp.model.response.AnswerResponse;

import java.util.ArrayList;
import java.util.List;

public class AnswerMapper {
    public static AnswerResponse toAnswerResponse(Answer answer) {
        return new AnswerResponse(answer.getId(), answer.getAnswer(),
                QuestionMapper.toQuestionResponse(answer.getQuestion()));
    }

    public static List<AnswerResponse> toAnswerResponse(List<Answer> answers) {
        List<AnswerResponse> answerResponses = new ArrayList<>();
        answers.forEach(answer -> answerResponses.add(toAnswerResponse(answer)));
        return answerResponses;
    }
}
