package com.teampc.dao.definitions.response;

import com.teampc.dao.DataDefinition;
import com.teampc.model.testtaking.QuestionResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.*;
import com.teampc.dao.definitions.question.QuestionDD;
import org.hibernate.annotations.GenericGenerator;

/**
 * QuestionResponseDD is a data-definition view of a QuestionResponse.
 *
 * It doesn't implement DataDefinition because its subclasses will do actual work and
 * transformation.
 *
 * @author David Ellison daelliso@calpoly.edu
 */
@Getter
@Setter
@Entity
@Table(name = "question_responses")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "q")
@Slf4j
public abstract class QuestionResponseDD implements DataDefinition<QuestionResponse> {
   @Id
   @GenericGenerator(name = "qrid", strategy = "increment")
   @GeneratedValue(generator = "qrid")
   @Column(name = "id")
   protected Integer id;

   @OneToOne
   @JoinColumn(name = "question_id")
   //@Column(name = "question_id")
   protected QuestionDD question;

   @Column(name = "points_received")
   protected Integer pointsReceived;

   public String toString() {
      return String.format(getClass().getSimpleName() + "\nId: %d, QuestionID: %d, ", id, question.getId());
   }
}
