package com.company.insuranceapp.model.request;

import lombok.Data;

@Data
public class UserQuestionRequest {
    private Long userId;
    private Long questionId;
}
