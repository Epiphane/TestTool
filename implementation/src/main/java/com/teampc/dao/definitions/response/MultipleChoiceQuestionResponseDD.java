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

import javax.persistence.*;

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
      log.debug("mcQuestionResponseDD.save() called on " + this + "\n");
      session.save(this);
      for (MultipleChoiceOptionDD option : choices) {
         option.setResponse(this);
         session.save(option);
      }
   }
}
