package com.company.insuranceapp.mapper;

import com.company.insuranceapp.model.entity.Question;
import com.company.insuranceapp.model.response.QuestionResponse;

public class QuestionMapper {
    public static QuestionResponse toQuestionResponse (Question question)
    {
        return new QuestionResponse(question.getId(), question.getQuestion(),
                question.getRightAnswer().getId());

    }
}
