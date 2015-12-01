package com.teampc.dao.definitions.question;

import com.teampc.dao.definitions.response.MultipleChoiceQuestionResponseDD;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The MultiplechoiceOptionDD class represents a database entity
 * view of a possible response for a MultipleChoiceQuestion.
 * These should not be dealt with directly by anything but
 * the owning MultipleChoiceQuestionDD object.
 *
 * @author David Ellison daelliso@calpoly.edu
 */
@Getter
@Setter
@Entity
@Table(name = "mc_options")
@Slf4j
public class MultipleChoiceOptionDD implements Comparable<MultipleChoiceOptionDD> {
   @Id
   @GenericGenerator(name = "optionid", strategy = "increment")
   @GeneratedValue(generator = "optionid")
   @Column(name = "id")
   private Integer id;

   @ManyToOne
   @JoinColumn(name = "response_id")
   private MultipleChoiceQuestionResponseDD response;

   @Column(name = "rank")
   private int rank;

   @Column(name = "option")
   private String text;

   /**
    * Allow the list of multiple choice questions to be sorted.
    * @param other another MultipleChoiceOptionDD belonging to the same MultipleChoiceQuestionDD.
    * @return an integer whose sign represents the ordering of the two MultiplechoiceOptionDD objects.
    *
    * pre:
    *   other != null
    * post:
    *
    */
   public int compareTo(MultipleChoiceOptionDD other) {
      if (this.rank > other.rank) {
         return 1;
      } else if (this.rank < other.rank) {
         return -1;
      } else {
         log.error(String.format("All multiple choice options for a particular question must have unique ranks. Question ID: %d", response.getId()));
         return 0;
      }
   }
}
