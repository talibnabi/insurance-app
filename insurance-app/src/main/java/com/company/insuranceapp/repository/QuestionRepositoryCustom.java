package com.company.insuranceapp.repository;

import com.company.insuranceapp.model.entity.Course;
import com.company.insuranceapp.model.entity.Question;

public interface QuestionRepositoryCustom {
    Question getRandomQuestionByCourse(Course course);
}
