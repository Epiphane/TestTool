package com.teampc.admin;

/**
 * Defines user details including name, username
 */
public abstract class User {
  /** Username used for login **/
  public String username;
  /** User's first name **/
  public String firstName;
  /** User's last name **/
  public String lastName;

  /**
   * Default constructor
   */
  public User() {

  }

  /**
   * Defines a user given username, firstName, and lastName
   */
  public User(String username, String firstName, String lastName) {

  }

  /**
   * Gets the user display name. It is defined as the firstName + lastName
   */
  public abstract String getDisplayName();
}
