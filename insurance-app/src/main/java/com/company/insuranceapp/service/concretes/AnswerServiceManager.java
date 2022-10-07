package com.company.insuranceapp.service.concretes;

import com.company.insuranceapp.model.entity.Answer;
import com.company.insuranceapp.model.entity.Question;
import com.company.insuranceapp.repository.AnswerRepository;
import com.company.insuranceapp.service.abstracts.AnswerService;
import com.company.insuranceapp.service.abstracts.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceManager  implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    @Override
    public Answer getById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Answer> getByQuestionId(Long id) {
        Question question = questionService.getById(id);
        return answerRepository.findByQuestion(question);
    }
}
