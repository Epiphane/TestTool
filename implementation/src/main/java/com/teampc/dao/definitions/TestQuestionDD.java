package com.teampc.dao.definitions;

import com.teampc.dao.definitions.question.QuestionDD;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * This class shouldn't be touched outside of TestDD
 *
 * @author david ellison daelliso@calpoly.edu
 */
@Getter
@Setter
@Entity
@Table(name = "test_questions")
public class TestQuestionDD implements Comparable<TestQuestionDD> {
   @Id
   @GenericGenerator(name = "tqid", strategy = "increment")
   @GeneratedValue(generator = "tqid")
   @Column(name = "id")
   private Integer id = null;

   @ManyToOne
   @JoinColumn(name = "question_id")
   private QuestionDD question;

   @ManyToOne
   @JoinColumn(name = "test_id")
   private TestDD test;

   @Column(name = "rank")
   private Integer rank;

   public int compareTo(TestQuestionDD other) {
      return this.rank.compareTo(other.rank);
   }
}
