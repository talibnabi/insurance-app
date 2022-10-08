package com.company.insuranceapp.repository;

import com.company.insuranceapp.model.entity.Course;
import com.company.insuranceapp.model.entity.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Random;

@Repository
public class QuestionRepositoryCustomImpl implements QuestionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Question getRandomQuestionByCourse(Course course) {
        Query query;
        if (course != null) {
            query = entityManager.createQuery("select q.id from Question q where q.course=:course");
        } else {
            query = entityManager.createQuery("select q.id from Question q where q.course is null ");
        }
        List<Long> ids = query.getResultList();
        if (ids.size() == 0) {
            return null;
        }

        Random random = new Random();
        Long randomId = ids.get(random.nextInt(ids.size()));

        query = entityManager.createQuery("select q from Question q where q.id=:id");
        query.setParameter("id", randomId);

        return (Question) query.getSingleResult();
    }
}
