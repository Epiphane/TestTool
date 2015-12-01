package com.teampc.dao.definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import com.teampc.dao.DataDefinition;
import com.teampc.dao.definitions.response.QuestionResponseDD;
import com.teampc.model.admin.access.UserSession;
import com.teampc.model.testtaking.Submission;
import com.teampc.model.testtaking.QuestionResponse;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.Session;

/**
 * A data-definition view of a test submission.
 *
 * @author David Ellison daelliso@calpoly.edu
 */
@Data
public class SubmissionDD implements DataDefinition<Submission> {
   @Id
   @GenericGenerator(name = "sid", strategy = "increment")
   @GeneratedValue(generator = "sid")
   @Column(name = "id")
   private Integer id;

   @OneToOne
   @JoinColumn(name = "test_id")
   private TestDD test;

   @Column(name = "user_id")
   private String userId;

   @OneToMany(mappedBy = "submission_id")
   private Set<QuestionResponseDD> questionResponses;

   public Submission asModel() {
      Submission s = new Submission();

      s.setTest(test.asModel());
      //TODO figure out how to turn a userId into a user
      //s.setTaker(userId);
      s.setTaker(UserSession.getUserlist().get(userId));


      s.setResponses(new ArrayList<>());

      return s;
   }

   public void save(Session session) {
      //TODO may need to revisit this if submission saving doesn't work right
      session.save(this);
   }
}
