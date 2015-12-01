package com.teampc.dao.definitions;

import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.sql.Date;
import javax.persistence.*;
import com.teampc.dao.DataDefinition;
import com.teampc.dao.QuestionDAO;
import com.teampc.dao.definitions.question.QuestionDD;
import com.teampc.model.question.Question;
import com.teampc.model.test.Test;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.annotations.GenericGenerator;

import java.util.stream.Collectors;

/**
 * The TestDD class is a data-definition class for tests
 *
 * @author David Ellison, daelliso@calpoly.edu
 */
@Data
@Entity
@Table(name = "tests")
public class TestDD implements DataDefinition<Test> {
   @Id
   @GenericGenerator(name = "tid", strategy = "increment")
   @GeneratedValue(generator = "tid")
   @Column(name = "id")
   private Integer id;

   @OneToOne
   @JoinColumn(name = "course_id")
   private CourseDD course;

   @Column(name = "title")
   private String title;

   @Column(name = "start_date")
   private Date startDate;

   @Column(name = "end_date")
   private Date endDate;

   @Column(name = "time_limit")
   private int timeAllowed;

   @OneToMany(mappedBy = "test")
   private Set<TestQuestionDD> questions;

   public Test asModel() {
      Test test = new Test();
      test.setId(id);
      test.setName(title);
      test.setStartDate(new java.util.Date(startDate.getTime()));
      test.setEndDate(new java.util.Date(endDate.getTime()));
      test.setTimeLimit(timeAllowed);
      test.setCourse(course.asModel());


      List<Integer> questionIds = questions.stream().map(TestQuestionDD::getId).sorted().collect(Collectors.toList());

      List<QuestionDD> questionDDs = QuestionDAO.getInstance().findByIds(questionIds);
      questionDDs.stream().sorted(new Comparator<QuestionDD>(){
         public int compare(QuestionDD a, QuestionDD b) {
            return new Integer(questionIds.indexOf(a.getId())).compareTo(
                  questionIds.indexOf(b.getId()));
         }
      });

      test.setQuestions(questionDDs.stream().map(QuestionDD::asModel).collect(Collectors.toList()));

      return test;
   }

   public void save(Session session) {
      session.save(this);
      for (TestQuestionDD testQuestion : questions) {
         testQuestion.setTest(this);
         session.save(testQuestion);
      }
   }
}
