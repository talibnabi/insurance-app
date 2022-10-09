package com.company.insuranceapp.mapper;

import com.company.insuranceapp.model.entity.Question;
import com.company.insuranceapp.model.response.QuestionResponse;

import java.util.ArrayList;
import java.util.List;

public class QuestionMapper {
    public static QuestionResponse toQuestionResponse (Question question)
    {
        return QuestionResponse.builder().id(question.getId())
                .question(question.getQuestion())
                .courseId((question.getCourse() != null) ? question.getCourse().getId() : null)
                .build();
    }
    public static List<QuestionResponse> toQuestionResponse(List<Question> questions) {
        List<QuestionResponse> questionResponses = new ArrayList<>();
        questions.forEach(course -> questionResponses.add(toQuestionResponse(course)));
        return questionResponses;
    }
}
