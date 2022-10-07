package com.company.insuranceapp.service.abstracts;

import com.company.insuranceapp.model.entity.Question;

public interface QuestionService {
    Question getById (Long id);

    Question getRandomQuestion ();
}
