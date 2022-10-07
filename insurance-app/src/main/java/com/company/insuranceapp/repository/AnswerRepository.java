package com.company.insuranceapp.repository;

import com.company.insuranceapp.model.entity.Answer;
import com.company.insuranceapp.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestion (Question question);
}
