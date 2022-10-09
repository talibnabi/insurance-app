package com.company.insuranceapp.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerResponse {
    private Long id;

    private String answer;

    private QuestionResponse questionResponse;

}
