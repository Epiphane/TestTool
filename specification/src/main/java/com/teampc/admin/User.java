package com.teampc.admin;

import lombok.Data;

/**
 * Defines user details including name, username
 */
@Data
public abstract class User {
  /** Username used for login **/
  private String username;
  /** User's first name **/
  private String firstName;
  /** User's last name **/
  private String lastName;

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
