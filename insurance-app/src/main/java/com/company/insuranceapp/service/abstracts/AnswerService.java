package com.company.insuranceapp.service.abstracts;

import com.company.insuranceapp.model.entity.Answer;

import java.util.List;

public interface AnswerService {
    Answer getById (Long id);

    List<Answer> getByQuestionId (Long id);
}
