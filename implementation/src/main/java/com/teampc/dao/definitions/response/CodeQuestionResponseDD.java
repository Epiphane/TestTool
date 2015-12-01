package com.teampc.dao.definitions.response;

import com.teampc.dao.DataDefinition;
import com.teampc.model.testtaking.CodeQuestionResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import javax.persistence.*;
/**
 * the CodeQuestionResponseDD class is a data-definition/persistence view
 * of a CodeQuestionResponse model object.
 */
@Getter
@Setter
@Entity
@DiscriminatorValue(value = "c")
@Slf4j
public class CodeQuestionResponseDD extends QuestionResponseDD {

   @Column(name = "c_response")
   private String codeResponse;

   @Column(name = "c_given")
   private String givenCode;

   //always the caller's responsibility to set the question
   public CodeQuestionResponse asModel() {
      CodeQuestionResponse r = new CodeQuestionResponse();

      r.setId(id);
      r.setPointsReceived(pointsReceived);
      r.setCodeAnswer(codeResponse);
      r.setGivenCode(givenCode);

      return r;
   }

   public void save(Session session) {
      session.save(this);
   }
}
