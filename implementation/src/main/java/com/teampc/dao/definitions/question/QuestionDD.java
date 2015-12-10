package com.teampc.dao.definitions.question;

import com.teampc.dao.QuestionResponseDAO;
import com.teampc.model.question.Question;
import com.teampc.dao.DataDefinition;
import com.teampc.dao.definitions.response.QuestionResponseDD;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.teampc.dao.HasId;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The QuestionDD class is a data-definition representation of a Question object.
 *
 * @author David Ellison, daelliso@calpoly.edu
 */
@Slf4j
@Getter
@Setter
@Entity
@Table(name = "questions")
public class QuestionDD implements DataDefinition<Question>  {
   @Id
   @GenericGenerator(name = "qid", strategy = "increment")
   @GeneratedValue(generator = "qid")
   @Column(name = "id")
   protected Integer id = null;

   @Column(name = "points")
   protected int points;

   @Column(name = "difficulty")
   protected int difficulty;

   @Column(name = "prompt")
   protected String prompt;

   @Column(name = "type")
   @Enumerated(EnumType.STRING)
   private Question.QuestionType type;

   @OneToOne
   //this might not work since there is no foreign key constraint
   @JoinColumn(name = "correct_response_id", nullable = true)
   //@Column(name = "correct_response_id")
   protected QuestionResponseDD correctAnswer;

   public Question asModel() {
      Question q = new Question();

      q.setDifficulty(difficulty);
      q.setPrompt(prompt);
      q.setPoints(points);
      q.setId(id);
      q.setType(type);
      q.setCorrectAnswer(correctAnswer.asModel());
      q.getCorrectAnswer().setQuestion(q);

      return q;
   }

   public String toString() {
      return String.format("QuestionDD:\nId: %d, prompt: %s, type: %s, answer: %s",
            id, prompt, type.name(), correctAnswer.toString());
   }

   public void save(Session session) {
      //log.debug("QuestionDD.save() called on " + this + "\n");
      session.save(this);
      getCorrectAnswer().setQuestion(this);
      getCorrectAnswer().save(session);
   }

   public void update(Session session) {
      log.debug("QuestionDD.update()");

      session.update(this);
      getCorrectAnswer().setQuestion(this);
      getCorrectAnswer().update(session);

   }

   public void delete(Session session) {
      log.debug("QuestionDD.delete()");

      //delete all
      QuestionResponseDAO.getInstance().deleteByQuestionId(this.id);

      session.delete(this);
   }
}
