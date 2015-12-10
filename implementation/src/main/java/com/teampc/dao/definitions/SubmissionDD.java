package com.teampc.dao.definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import com.teampc.dao.DataDefinition;
import com.teampc.dao.definitions.response.QuestionResponseDD;
import com.teampc.model.admin.access.UserSession;
import com.teampc.model.test.Test;
import com.teampc.model.testtaking.Submission;
import com.teampc.model.testtaking.QuestionResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.Session;

/**
 * A data-definition view of a test submission.
 *
 * @author David Ellison daelliso@calpoly.edu
 */
@Slf4j
@Data
@Entity
@Table(name = "submissions")
public class SubmissionDD implements DataDefinition<Submission> {
   @Id
   @GenericGenerator(name = "sid", strategy = "increment")
   @GeneratedValue(generator = "sid")
   @Column(name = "id")
   private Integer id;

   @ManyToOne
   @JoinColumn(name = "test_id")
   private TestDD test;

   @Column(name = "user_id")
   private String userId;

   @OneToMany(mappedBy = "submission")
   private Set<QuestionResponseDD> questionResponses;

   @Column(name = "complete")
   private Integer complete;

   @Column(name = "is_graded")
   private Integer graded;


   public Submission asModel() {
      Submission s = new Submission();

      s.setId(id);
      s.setTaker(UserSession.getUserlist().get(userId));

      s.setResponses(new ArrayList<>());
      for (QuestionResponseDD response : questionResponses) {
         s.getResponses().add(response.asModel());
      }
      s.setComplete(complete == 1);
      s.setGraded(graded == 1);

      return s;
   }

   /**
    * Infinite recursion is bad, so we use this method
    *
   public Submission asModel(Test t) {
      Submission s = new Submission();

      s.setTest(t);

      s.setId(id);
      s.setTaker(UserSession.getUserlist().get(userId));

      s.setResponses(new ArrayList<>());
      for (QuestionResponseDD response : questionResponses) {
         s.getResponses().add(response.asModel());
      }
      s.setComplete(complete == 1);
      s.setGraded(graded == 1);

      return s;
   }*/

   public void save(Session session) {
      //TODO may need to revisit this if submission saving doesn't work right
      session.save(this);
      for (QuestionResponseDD response : questionResponses) {
         response.setSubmission(this);
         response.save(session);
      }
   }

   public void update(Session session) {
      session.update(this);

      for (QuestionResponseDD response : questionResponses) {
         response.update(session);
      }
      /*
      Uncomment this is deleting/re-adding is necessary.
      for (QuestionResponseDD response : questionResponses) {
         System.out.println(response);
         response.setSubmission(this);
         session.save(response);
      }*/
   }

   public void delete(Session session) {

      for (QuestionResponseDD response : questionResponses) {
         response.delete(session);
      }
      session.delete(this);
   }
}
