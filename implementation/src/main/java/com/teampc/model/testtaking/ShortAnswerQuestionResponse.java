package com.teampc.model.testtaking;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * A response to a short answer question.
 * @author Zach Arend
 */

@AllArgsConstructor
public class ShortAnswerQuestionResponse extends QuestionResponse<ShortAnswerQuestionResponse> {
   @Getter
   @Setter
   private String answer = "";

   @Getter
   @Setter
   private MatchType matchType;

    /**
     * Copy constructor
     */
   public ShortAnswerQuestionResponse(ShortAnswerQuestionResponse questionResponse) {
      this.answer = questionResponse.answer;
      this.matchType = questionResponse.matchType;
   }

   /**
    * Student version of question response
     */
   public static ShortAnswerQuestionResponse studentResponse(String answer) {
      return new ShortAnswerQuestionResponse(answer, null);
   }

   /** {@inheritDoc} */
   @Override
   public boolean isComplete() {
      return true;
   }

   /**
    pre: questionResponse != null && maxPoints > 0

    post: questionResponse.pointsReceived >= 0 && questionResponse.pointsReceived <= maxPoints
    */
   @Override
   public void assignPoints(ShortAnswerQuestionResponse questionResponse, int maxPoints) {
      Collection<String> keywords = Stream.of(answer.split(","))
         .map(String::trim)
         .filter(keyword -> !Strings.isNullOrEmpty(keyword))
         .collect(toList());
      questionResponse.pointsReceived = matchType.grade(questionResponse.getAnswer(), maxPoints, keywords);
   }

   /**
    * {@inheritDoc}
     */
   @Override
   public ShortAnswerQuestionResponse copy() {
      return new ShortAnswerQuestionResponse(this);
   }

   @Override
   public String toString() {
      return answer;
   }

   public enum MatchType {
      EXACTLY {
         @Override
         public int grade(String shortAnswer, int maxPoints, Collection<String> keywords) {
            String regex = keywords.stream().collect(joining("\\s*,\\s*"));
            return shortAnswer.matches(regex) ? maxPoints : 0;
         }
      },
      ANY {
         @Override
         public int grade(String shortAnswer, int maxPoints, Collection<String> keywords) {
            return keywords.stream().anyMatch(shortAnswer::contains) ? maxPoints : 0;
         }
      },
      ALL {
         @Override
         public int grade(String shortAnswer, int maxPoints, Collection<String> keywords) {
            long numFound = keywords.stream().filter(shortAnswer::contains).count();
            float percentageCorrect = ((float) numFound) / ((float) keywords.size());
            return (int) (maxPoints * percentageCorrect);
         }
      };

      public abstract int grade(String shortAnswer, int maxPoints, Collection<String> keywords);
   }

}
