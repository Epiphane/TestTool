package com.teampc.model.admin;

import lombok.Data;

/**
 * Defines user details including name, username
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
   public abstract UserType getType();

   public enum UserType {
      STUDENT,
      TEACHER
   }
}

