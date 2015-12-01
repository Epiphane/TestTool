package com.teampc.dao.definitions.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * MatchingQuestionPairDD class is a persistent entity for matching question
 * pairs. Only the owning MatchingQuestionDD object should deal with
 * MatchingQuestionPairDDs.
 *
 * @author David Ellison daelliso@calpoly.edu
 */
@Getter
@Setter
@Entity
@Table(name = "matching_pairs")
public class MatchingQuestionPairDD {
   @Id
   @GenericGenerator(name = "mpairid", strategy = "increment")
   @GeneratedValue(generator = "mpairid")
   @Column(name = "id")
   private Integer id;

   @ManyToOne
   @JoinColumn(name = "response_id")
   private MatchingQuestionResponseDD question;

   @Column(name = "prompt")
   private String prompt;

   @Column(name = "response")
   private String response;
}
