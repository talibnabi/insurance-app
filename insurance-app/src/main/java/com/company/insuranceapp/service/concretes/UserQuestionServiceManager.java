package com.company.insuranceapp.service.concretes;

import com.company.insuranceapp.model.entity.Question;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.entity.UserQuestion;
import com.company.insuranceapp.repository.UserQuestionRepository;
import com.company.insuranceapp.service.abstracts.UserQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQuestionServiceManager implements UserQuestionService {
    private final UserQuestionRepository userQuestionRepository;

    @Override
    public void passQuestion(UserQuestion userQuestion) {
        userQuestionRepository.save(userQuestion);
    }

    @Override
    public Boolean isQuestionPassed(User user, Question question) {
        return userQuestionRepository.findByUserAndQuestion(user, question) != null;
    }
}
