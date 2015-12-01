package com.teampc.dao.definitions;

import javax.persistence.*;
import com.teampc.dao.DataDefinition;
import com.teampc.model.admin.User;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 * A data-definition class for a Test Tool user.
 *
 * @author david ellison, daelliso@calpoly.edu
 */
@Data
@Entity
@Table(name="users")
public class UserDD {
   @Id
   @GenericGenerator(name = "uid", strategy = "increment")
   @GeneratedValue(generator = "uid")
   @Column(name = "id")
   private Integer id;

   @Column(name = "login_name")
   private String username;

   @Column(name = "first_name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "password")
   private String password;

   /*
   public User asModel() {
      User user = new User();
      user.setId(id);
      user.setUsername(username);
      user.setFirstName(firstName);
      user.setLastName(lastName);
      user.setPassword(password);
      return user;
   }*/
}
