package com.teampc.dao;

import com.teampc.model.Model;
import org.hibernate.Session;

/**
 * An interface for data-definition classes to prevent
 * use of persistence objects everywhere. The asModel() method
 * converts a DD object into the appropriate model object.
 *
 * @author David Ellison, daelliso@calpoly.edu
 */
public interface DataDefinition<M extends Model> {
   /**
    * Turn the object into a model version of the same
    * thing. i.e. MultipleChoiceQuestionDD.asModel() will return
    * an equivalent object of class com.teampc.model.question.MultipleChoiceQuestion
    * which should be used in favor of the Hibernate (*DD.java) entities.
    * @return an equivalent model representation of the DataDefinition implementing object.
    */
   public M asModel();

   public void save(Session hibernateSession);
}
