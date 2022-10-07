package com.company.insuranceapp.repository;

import com.company.insuranceapp.model.entity.Question;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Random;

public class QuestionRepositoryCustomImpl implements QuestionRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Question getRandomQuestion() {
        Query query = entityManager.createQuery("select q.id from Question q");
        List<Long> ids = query.getResultList();

        Random random = new Random();
        Long randomId = ids.get(random.nextInt(ids.size()));

        query = entityManager.createQuery("select q from Question q where q.id=:id");
        query.setParameter("id", randomId);

        return (Question) query.getSingleResult();
    }
}
