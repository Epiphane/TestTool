package com.teampc.dao.definitions.response;

import static java.util.stream.Collectors.toList;

import com.teampc.dao.DataDefinition;
import com.teampc.dao.definitions.question.MultipleChoiceOptionDD;
import com.teampc.model.testtaking.MultipleChoiceQuestionResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

/**
 * The MultipleChoiceQuestionResponseDD class is a
 * data-definition representation of a MultipleChoiceQuestionResponse
 * model object.
 *
 * @author David Ellison daelliso@calpoly.edu
 */
@Getter
@Setter
@Entity
@DiscriminatorValue(value = "mc")
@Slf4j
public class MultipleChoiceQuestionResponseDD extends QuestionResponseDD {

   @Column(name = "mc_response")
   private int responseRank;

   @OneToMany(mappedBy = "response", cascade = CascadeType.ALL)
   private Set<MultipleChoiceOptionDD> choices;

   public MultipleChoiceQuestionResponse asModel() {
      MultipleChoiceQuestionResponse m = new MultipleChoiceQuestionResponse();

      m.setId(id);
      m.setPointsReceived(pointsReceived);

      m.setChoices(choices.stream().sorted()
            .map(MultipleChoiceOptionDD::getText).collect(toList()));

      //Don't set the question because the Question model object
      //should be the owner of its responses, and should be in charge
      //m.setQuestion(((MultipleChoiceQuestionDD) question).asModel())


      m.setAnswer(responseRank);
      return m;
   }

   public String toString() {
      return super.toString() + String.format("Response rank: %d. choices: %s",
            responseRank, choices);
   }

   public void save(Session session) {
      //log.debug("MultipleChoiceQuestionResponseDD.save");
      //log.debug(this.toString());
      session.save(this);
      for (MultipleChoiceOptionDD option : choices) {
         option.setResponse(this);
         session.save(option);
      }
   }

   public void update(Session session) {
      log.debug("mcQuestionResponseDD.update()");
      session.update(this);

      deleteOldOptions(session);

      for (MultipleChoiceOptionDD option : choices) {
         option.setResponse(this);
         session.save(option);
      }
   }

   public void delete(Session session) {
      deleteOldOptions(session);
      session.delete(this);
   }


   private void deleteOldOptions(Session session) {
      List<MultipleChoiceOptionDD> oldOptions =
            session.createCriteria(MultipleChoiceOptionDD.class)
            .add(Restrictions.eq("response_id", this.id))
            .list();

      for (MultipleChoiceOptionDD oldOption : oldOptions) {
         session.delete(oldOption);
      }
   }
}
