package com.company.insuranceapp.model.response;


import com.company.insuranceapp.model.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerResponse {
    private Long id;

    private String answer;

    private Question question;

}
