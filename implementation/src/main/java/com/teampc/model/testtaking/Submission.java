package com.teampc.model.testtaking;

import com.teampc.dao.HasId;
import com.teampc.dao.definitions.SubmissionDD;
import com.teampc.dao.definitions.response.QuestionResponseDD;
import com.teampc.model.Model;
import com.teampc.model.admin.*;
import com.teampc.model.test.*;
import java.util.*;

import com.teampc.model.automation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.stream.Collectors.toList;

/**
 * An instance of an in-progress test being taken
 * @author Thomas Steinke
 *
 */
@Data
public class Submission implements Model<SubmissionDD> {

   private Integer id;

   private Test test;

   public User taker;

   public List<QuestionResponse> responses = new ArrayList<>();

   private boolean complete;

   public float grade;

   @Getter
   private boolean isGraded = false;

   public Submission(Test test, User user, List<QuestionResponse> responses, boolean complete) {
      this.test = test;
      this.taker = user;
      this.responses = responses;
      this.complete = complete;
   }

    /**
     * Copy constructor
     */
   public Submission(Submission submission) {
      this.id = submission.id;
      this.test = submission.test;
      this.taker = submission.taker;
      this.responses = submission.responses.stream().map(QuestionResponse::copy).collect(toList());
      this.complete = submission.complete;
      this.grade = submission.grade;
   }

   /**
    * Return whether the test is complete or not
    */
   public boolean isComplete() {
      return complete;
   }

   /**
    * Determine how many questions have been completed so far.
    */
   public int questionsCompleted() {
      return 0;
   }

   public int getNextUnansweredQuestion() {
      return 0;
   }

   /**
    * Grade the test against a teacher's key
    */
   public void gradeTest(Key correctAnswers) {
      TestGrader.gradeTest(this, correctAnswers);

      grade = responses.stream()
         .map(QuestionResponse::getPointsReceived)
         .reduce(0, Math::addExact);

      isGraded = true;
   }


   public SubmissionDD asEntity() {
      SubmissionDD submission = new SubmissionDD();

      submission.setUserId(taker.getUsername());
      submission.setId(id);
      //HACK: test.asEntity calls question.asEntity
      //which sets the question fields on QuestionResponse,
      //which are NOT set by QuestionResponse.asEntity.
      submission.setTest(test.asEntity());
      //submission.setUser();

      submission.setQuestionResponses(new HashSet<QuestionResponseDD>());

      for (QuestionResponse response : responses) {
         submission.getQuestionResponses().add((QuestionResponseDD) response.asEntity());
      }

      return submission;
   }
}
