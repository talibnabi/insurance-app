package com.company.insuranceapp.repository;

import com.company.insuranceapp.model.entity.Question;
import com.company.insuranceapp.model.entity.User;
import com.company.insuranceapp.model.entity.UserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestionRepository extends JpaRepository<UserQuestion, Long> {
    UserQuestion findByUserAndQuestion (User user, Question question);

    Long countByUserAndPoint (User user, Boolean point);
}