package com.teampc.model.admin;

import lombok.Data;

import java.util.Scanner;

/**
 * User class defines user details including name, username
 *
 * @author Greg Sawers (gsawers@calpoly.edu)
 */
@Data
public abstract class User {

   /**
    * Unique User ID
    **/
   private int userId;
   /**
    * Username used for login
    **/
   private String username;
   /**
    * User's first name
    **/
   private String firstName;
   /**
    * User's last name
    **/
   private String lastName;

   /**
    * User's password
    **/
   private String password;

   /**
    * Is admin user
    */
   private final boolean admin;

   /**
    * Default constructor
    */
   public User() {
      admin = false;
   }

   /**
    * Defines a user given username, firstName, lastName, and password
    *
    * @param username  Username of the User
    * @param firstName First name of the User
    * @param lastName  Last Name of the User
    * @param password  Password of the User
    */
   public User(String username, String firstName, String lastName, String password) {
      this.username = username;
      this.firstName = firstName;
      this.lastName = lastName;
      this.password = password;
      admin = false;
   }

   /**
    * Defines a user given username, firstName, lastName, password, and role
    *
    * @param username  Username of the User
    * @param firstName First name of the User
    * @param lastName  Last Name of the User
    * @param password  Password of the User
    */
   public User(String username, String firstName, String lastName, String password, boolean admin) {
      this.username = username;
      this.firstName = firstName;
      this.lastName = lastName;
      this.password = password;
      this.admin = admin;
   }

   /**
    * Defines a user given a username, firstName and lastName.
    *
    * @param username  Username of the User
    * @param firstName First name of the User
    * @param lastName  Last Name of the user
    */
   @Deprecated
   public User(String username, String firstName, String lastName) {
      this.username = username;
      this.firstName = firstName;
      this.lastName = lastName;
      admin = false;
   }

   /**
    * Copy constructor
     */
   public User(User user) {
      this.userId = user.userId;
      this.username = user.username;
      this.firstName = user.firstName;
      this.lastName = user.lastName;
      this.password = user.password;
      this.admin = user.admin;
   }

   /**
    * Gets the user display name. It is defined as the firstName + lastName
    * <p>
    * pre: username != null
    */
   public String getDisplayName() {
      return firstName + " " + lastName;
   }

   /**
    * User's type
    */
   public abstract <T> T accept(Visitor<T> visitor);

   public enum UserType {
      STUDENT,
      TEACHER
   }

   public interface Visitor<T> {
      T visitTeacher(Teacher t);
      T visitStudent(Student s);
   }

   /**
    * Reads user information from file
    * @param serialized The string to interpret the user from.
    pre: serialized != null && serialized.split("\\s").length >= 5

    post: return != null
    */
   public static User fromString(String serialized) {
      Scanner lineScanner = new Scanner(serialized);
      String type = lineScanner.next();
      String username = lineScanner.next();
      String firstName = lineScanner.next();
      String lastName = lineScanner.next();
      String password = lineScanner.next();
      boolean isAdmin = false;
      if (lineScanner.hasNext()) {
         isAdmin = Boolean.valueOf(lineScanner.next());
      }

      User user;

      UserType userType = UserType.valueOf(type.toUpperCase());
      // Need to do it this ugly way because we can't hit the default case on a
      // switch if we have all the cases, but static analysis can't catch this.
      if (userType == UserType.TEACHER) {
         user = new Teacher(username, firstName, lastName, password, isAdmin);
      } else {
         user = new Student(username, firstName, lastName, password, isAdmin);
      }

      return user;
   }

   /**
    *
    * @return The type of user this user is;
    */
   public UserType getUserType() {
      return accept((Visitor<UserType>) new Visitor<UserType>() {
         @Override
         public UserType visitTeacher(Teacher t) {
            return UserType.TEACHER;
         }

         @Override
         public UserType visitStudent(Student s) {
            return UserType.STUDENT;
         }
      });
   }

   public boolean isTeacher() {
      return getUserType().equals(UserType.TEACHER);
   }

   public boolean isStudent() {
      return getUserType().equals(UserType.STUDENT);
   }
}

