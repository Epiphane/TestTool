package com.teampc.dao;

import com.teampc.dao.definitions.response.QuestionResponseDD;
import com.teampc.model.testtaking.*;
import com.teampc.utils.HibernateUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

/**
 * a DAO implementation for dealing with QuestionResponses
 *
 * @author david ellison daelliso@calpoly.edu
 */
public class QuestionResponseDAO extends AbstractDAO<QuestionResponse, QuestionResponseDD> {

   private static QuestionResponseDAO instance = null;

   public static synchronized QuestionResponseDAO getInstance() {
      if (instance == null) {
         instance = new QuestionResponseDAO();
      }
      return instance;
   }

   private QuestionResponseDAO() {}

   @Override
   protected Class<QuestionResponseDD> getEntityClass() {
      return QuestionResponseDD.class;
   }

   //SPECIAL BEHAVIOR FOR QuestionResponseDAO:
   //On save, MultipleChoiceQuestionResponses should clear all relevant
   //rows from mc_option and re-save their choices.
   //On save, MatchingQuestionResponses should clear all relevant rows from
   //matching_pairs and re-save their pairs.

/*
   @Override
   public void insert(Collection<QuestionResponse> items) {

   }

   @Override
   public void insert(QuestionResponse item) {

   }

   @Override
   public List<QuestionResponse> fetchAll() {

   }

   @Override
   public void delete(QuestionResponse item) {

   }

   @Override
   public void delete(Collection<QuestionResponse> items) {

   }

   @Override
   public void update(QuestionResponse item) {

   }

   @Override
   public void update(Collection<QuestionResponse> items) {

   }

   private QuestionResponseDD toDD(QuestionResponse response) {

   }

   private Collection<QuestionResponseDD> toDD(Collection<QuestionResponse> responses) {

   }

   private List<QuestionResponse> fromDD(Collection<QuestionResponseDD> dds) {

   }*/
}
