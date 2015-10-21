package com.teampc.admin.access;

import com.teampc.admin.User;

/**
 * Identifies the user that is currently logged into the application.
 * Users can have set restrictions, to prevent or allow access to parts
 * of the application
 */
public abstract class UserSession {
  /** Current logged in user **/
  public User loggedInUser;

  /**
   * A session is defined by the user that is logged in
   */
  public UserSession(User user) {

  }

  /**
   * Get the currently logged in user
   */
  public abstract User getLoggedInUser();

  /**
   * Ends the current user session
   */
  public abstract void endUserSession();
}
