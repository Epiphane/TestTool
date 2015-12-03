package com.teampc.dao;

import com.teampc.model.question.Question;

/**
 * Created by adufrene on 11/9/15.
 */
public class QuestionDAO extends AbstractDAO<Question> {

   private static QuestionDAO instance = null;

   public static synchronized QuestionDAO getInstance() {
      if (instance == null) {
         instance = new QuestionDAO();
      }
      return instance;
   }

   private QuestionDAO() {}

   @Override
   protected Class<Question> getEntityClass() {
      return Question.class;
   }

   @Override
   protected int getId(Question item) {
      return item.getId();
   }

   @Override
   protected void setId(Question item, int id) {
      item.setId(id);
   }
}
