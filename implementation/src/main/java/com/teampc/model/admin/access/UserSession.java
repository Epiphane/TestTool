package com.teampc.model.admin.access;

import com.teampc.model.admin.User;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Scanner;

/**
 * UserSession identifies the user that is currently logged into the application.
 * Users can have set restrictions, to prevent or allow access to parts
 * of the application
 *
 * @author Greg Sawers gsawers (gsawers@calpoly.edu)
 */
@Slf4j
public class UserSession {

   /** Current logged in user **/
   public static User loggedInUser;

   /**
    * A check to see if a user is logged in.
    */
   public static boolean loggedIn = false;

   /**
    * List of Users organized by Username
    */
   private static HashMap<String, User> userlist = new HashMap<String, User>();

   /**
    *
    * @return Returns the list of Users registered
    */
   public static HashMap<String, User> getUserlist(){return userlist;}


   /**
    *
    * Check to see if the username and password are correct.
    * @param username the username used to login.
    * @param password the password used to login
    *
    *@return Return true if the user exists and the password is correct, else return false
    *
     pre: username != null && password != null
    *
     post: (loggedIn == true && userList.containsKey(username)) || (loggedIn == false && !userList.containsKey(username))
    */
   public static boolean login(String username, String password){
      /**
       * Build the list of users. Will be replaced with a Database hook later.
       */
      populateUserList();
      /**
       * If the username exists and the password is correct for thr user, log the user in and return true
       */
      if(userlist.containsKey(username) && userlist.get(username).getPassword().equals(password))  {
         User user = userlist.get(username);
         loggedInUser = user;
         loggedIn = true;
         return true;
      }
      /**
       * Otherwise return false
       */
      else {
         log.error("Invalid username or password");
         return false;
      }
   }

   /**
    * Register a new User with the application
    * @param username The username of the new User
    * @param pass The password of the new User
    * @param first The first name of the new User
    * @param last The last name of the new USer
    */
   public static void Register(String username, String pass, String first, String last){
      User user = new User(username, first, last, pass, false);
      userlist.put(username, user);
      registerUser(username, user);
   }

   /**
    * Get the currently logged in user
    *
    * @return User an instance of the logged in User
    *
     pre: loggedIn && loggedInUser != null
    *
     post: loggedIn && loggedInUser != null
    */
   public static User getLoggedInUser(){

      log.debug(loggedInUser.getDisplayName() + " is logged in");
      return loggedInUser;
   }

   /**
    * Ends the current user session
    *
       pre: loggedIn
    *
       post: !loggedIn && loggedInUser == null;
    */
   public static void endUserSession(){
      loggedIn = false;
      loggedInUser = null;
   }

   private static void populateUserList(){
      String filename = "users.txt", line, username, firstName, lastName, password;
      Scanner fileScan = null, linescan;
      boolean isAdmin = false;
      File file = new File(filename);

      try{

         if (!file.createNewFile()) {
            log.debug("File already made");
         }

         fileScan = new Scanner(file);

         while(fileScan.hasNextLine()) {
            line = fileScan.nextLine();
            linescan = new Scanner(line);
            username = linescan.next();
            firstName = linescan.next();
            lastName = linescan.next();
            password = linescan.next();
            isAdmin = false;
            if (linescan.hasNext()) {
               isAdmin = Boolean.valueOf(linescan.next());
            }
            User user = new User(username, firstName, lastName, password, isAdmin);
            userlist.put(username, user);
         }
      }
      catch(IOException e){
         e.printStackTrace();
      }


   }


   private static void registerUser(String username, User user){

      String fileName = "";

      String output = username + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getPassword() + "\n";

      try {
         Files.write(Paths.get("users.txt"), output.getBytes(), StandardOpenOption.APPEND);
      }catch (IOException e) {
        e.printStackTrace();
      }
   }
}
