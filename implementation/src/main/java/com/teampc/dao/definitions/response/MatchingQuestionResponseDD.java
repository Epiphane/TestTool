package com.teampc.dao.definitions.response;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import com.teampc.dao.AbstractDAO;
import com.teampc.dao.DataDefinition;
import com.teampc.model.testtaking.MatchingQuestionResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import javax.persistence.*;

/**
 * The MatchingQuestionResponseDD class is a data-definition/persistence
 * representation of a MatchingQuestionResponse model object.
 *
 * @author david ellison, daelliso@calpoly.edu
 */
@Getter
@Setter
@Entity
@DiscriminatorValue(value = "ma")
@Slf4j
public class MatchingQuestionResponseDD extends QuestionResponseDD {

   @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
   private Set<MatchingQuestionPairDD> pairs;

   public MatchingQuestionResponse asModel() {
      MatchingQuestionResponse r = new MatchingQuestionResponse();

      Map<String, String> pairings = new HashMap<>();
      for (MatchingQuestionPairDD pairing : pairs) {
         pairings.put(pairing.getPrompt(), pairing.getResponse());
      }
      r.setPairings(pairings);

      r.setPointsReceived(pointsReceived);
      r.setId(id);

      return r;
   }

   public void save(Session session) {
      session.save(this);
      for (MatchingQuestionPairDD pairing : pairs) {
         pairing.setQuestion(this);
         session.save(pairing);
      }
   }
}
