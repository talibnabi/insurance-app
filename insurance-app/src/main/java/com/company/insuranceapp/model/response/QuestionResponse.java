package com.company.insuranceapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String question;
    private List<String> answers;
    private Long rightAnswerIndex;
    private Long courseId;
}
