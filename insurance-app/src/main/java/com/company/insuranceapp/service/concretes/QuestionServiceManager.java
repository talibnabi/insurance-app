package com.company.insuranceapp.service.concretes;

import com.company.insuranceapp.model.entity.Course;
import com.company.insuranceapp.model.entity.Question;
import com.company.insuranceapp.repository.QuestionRepository;
import com.company.insuranceapp.service.abstracts.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceManager implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Question getById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public Question getRandomQuestionByCourse(Course course) {
        return questionRepository.getRandomQuestionByCourse(course);
    }
}
