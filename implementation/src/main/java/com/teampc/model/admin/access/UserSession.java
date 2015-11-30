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

   public boolean loggedIn = false;

   /**
    * A session is defined by the user that is logged in
    */
   public UserSession(User user) {

   }

   /**
     pre: user != null
    *
     post: loggedIn == true && loggedInUser.equals(user)
    */
   public void login(User user){
      loggedInUser = user;
      loggedIn = true;
      System.out.println("User gsawers logged in to TestTool.");
   }

   /**
    * Get the currently logged in user
    *
     pre: loggedIn && loggedInUser != null
    *
     post: loggedIn && loggedInUser != null
    */
   public User getLoggedInUser(){

      System.out.println("gsawers is currently logged in");
      return new User();
   }

   /**
    * Ends the current user session
    *
       pre: loggedIn
    *
       post: !loggedIn && loggedInUser == null
    */
   public void endUserSession(){

   }
}
