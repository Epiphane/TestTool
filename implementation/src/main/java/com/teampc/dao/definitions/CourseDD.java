package com.teampc.dao.definitions;

import com.teampc.model.admin.access.UserSession;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import com.teampc.dao.DataDefinition;
import com.teampc.model.admin.course.Course;
import com.teampc.model.admin.course.Term;
import com.teampc.model.admin.Teacher;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.annotations.GenericGenerator;

/**
 * a data-definition object for courses
 *
 * @author daelliso
 */
@Getter
@Setter
@Entity
@Table(name = "courses")
public class CourseDD implements DataDefinition<Course> {
   @Id
   @GenericGenerator(name = "cid", strategy = "increment")
   @GeneratedValue(generator = "cid")
   @Column(name = "id")
   private Integer id;

   @Column(name = "owner_id")
   private String userId;

   @Column(name = "title")
   private String title;

   @Column(name = "year")
   private int year;

   @Column(name = "section")
   private int section;

   @Column(name = "term")
   @Enumerated(EnumType.STRING)
   private Term term;

   /**
    * Turn this data entity object into an equivalent model object.
    * @return a model.admin.course.Course object equivalent to the persistence store's representation.
    */
   public Course asModel() {
      Course c = new Course(title, term, year, section);
      c.setTeacher((Teacher)UserSession.getUserlist().get(userId));

      return c;
   }

   public void save(Session session) {
      session.save(this);
   }

   public Term getTerm() {
      return term;
   }
}
