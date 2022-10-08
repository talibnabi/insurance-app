package com.company.insuranceapp.service.abstracts;

import com.company.insuranceapp.model.entity.Question;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.entity.UserQuestion;

public interface UserQuestionService {
    void passQuestion(UserQuestion userQuestion);

    Boolean isQuestionPassed(User user, Question question);

    Long getUserPoint(User user);
}
