package com.teampc.dao.definitions.response;

import com.teampc.dao.DataDefinition;
import com.teampc.model.testtaking.ShortAnswerQuestionResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import javax.persistence.*;

/**
 * The ShortAnswerQuestionResponseDD class is a data-definition
 * persistence representation of a ShortAnswerQuestionResponse model object.
 *
 * @author david ellison daelliso@calpoly.edu
 */
@Getter
@Setter
@Entity
@DiscriminatorValue(value = "sa")
@Slf4j
public class ShortAnswerQuestionResponseDD extends QuestionResponseDD {
   @Column(name = "sa_response")
   private String answer;

   @Column(name = "sa_mtype")
   @Enumerated(EnumType.STRING)
   private ShortAnswerQuestionResponse.MatchType type;

   public ShortAnswerQuestionResponse asModel() {
      ShortAnswerQuestionResponse s = new ShortAnswerQuestionResponse();

      s.setId(id);
      s.setPointsReceived(pointsReceived);

      s.setAnswer(answer);
      s.setMatchType(type);

      return s;
   }

   public String toString() {
      return super.toString() + String.format("MatchType: %s, answer: %s",
            type.name(), answer);
   }

   public void save(Session session) {
      session.save(this);
   }

   public void update(Session session) {
      session.update(this);
   }

   public void delete(Session session) {
      session.delete(this);
   }
}
