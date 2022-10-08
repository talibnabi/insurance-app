package com.company.insuranceapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String question;
    private Long rightAnswerId;
    private Long courseId;
}
