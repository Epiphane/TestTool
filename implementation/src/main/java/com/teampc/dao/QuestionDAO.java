package com.teampc.dao;

import com.teampc.dao.definitions.response.QuestionResponseDD;
import com.teampc.utils.HibernateUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import com.teampc.model.question.Question;
import com.teampc.dao.definitions.question.QuestionDD;

/**
 * Created by adufrene on 11/9/15.
 */
@Slf4j
public class QuestionDAO extends AbstractDAO<Question, QuestionDD> {

   private static QuestionDAO instance = null;

   public static synchronized QuestionDAO getInstance() {
      if (instance == null) {
         instance = new QuestionDAO();
      }
      return instance;
   }

   private QuestionDAO() {}

   @Override
   protected Class<QuestionDD> getEntityClass() {
      return QuestionDD.class;
   }

/*
   @Override
   public void insert(Collection<Question> items) {
      log.debug("Inserting questions with QuestionDAO.insert()");
      List<QuestionDD> questionDDs = toDD(items);

      log.debug("First question: " + questionDDs.get(0));

      List<QuestionResponseDD> correctAnswersToSave = new ArrayList<>();

      questionDDs.stream().map(QuestionDD::getCorrectAnswer)
            .forEach((response)->correctAnswersToSave.add(response));

      Session session = HibernateUtils.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();

      try {
         questionDDs.forEach(question->{
            session.save(question);
            //save the correct answer
            question.getCorrectAnswer().setQuestion(question);
            session.save(question.getCorrectAnswer());
         });
         session.flush();
         transaction.commit();
      } catch (Exception e) {
         log.error("Error during insert, rolling back");
         transaction.rollback();
         throw e;
      } finally {
         session.close();
      }
   }

   @Override
   public List<Question> fetchAll() {
      log.debug("QuestionDAO.fetchAll()");
      Session session = HibernateUtils.getSessionFactory().openSession();
      try {
         Criteria criteria = session.createCriteria(getEntityClass());

         List<QuestionDD> items = criteria.list();
         log.debug("Got " + items.size() + " items from questions table");
         return fromDD(items);
      } finally {
         session.close();
      }
   }

   @Override
   public void delete(Question item) {

   }

   private List<QuestionDD> toDD(Collection<Question> q) {
      List<QuestionDD> asDD = new ArrayList<QuestionDD>();
      for (Question question : q) {
         asDD.add(question.asEntity());
      }

      return asDD;
   }

   private List<Question> fromDD(Collection<QuestionDD> dds) {
      List<Question> questions = new ArrayList<>();

      for (QuestionDD dd : dds) {
         questions.add(dd.asModel());
      }

      return questions;
   }*/

}
