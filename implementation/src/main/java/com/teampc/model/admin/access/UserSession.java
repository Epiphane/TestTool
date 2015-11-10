package com.teampc.model.admin.access;

import com.teampc.model.admin.User;

/**
 * Identifies the user that is currently logged into the application.
 * Users can have set restrictions, to prevent or allow access to parts
 * of the application
 */
public class UserSession {

   /** Current logged in user **/
   public User loggedInUser;

   /**
    * A session is defined by the user that is logged in
    */
   public UserSession(User user) {

   }

   /**
    * pre:
    *
    * post:
    */
   public void login(){
      System.out.println("User gsawers logged in to TestTool.");
   }

   /**
    * Get the currently logged in user
    *
    * pre:
    *
    * post:
    */
   public User getLoggedInUser(){

      System.out.println("gsawers is currently logged in");
      return new User();
   }

   /**
    * Ends the current user session
    */
   public void endUserSession(){

   }
}
