package com.teampc.model.admin.access;

import com.teampc.model.admin.Student;
import com.teampc.model.admin.Teacher;
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

   public static final String USER_FILE = "users.txt";

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
     pre: username != null && password != null && loggedIn == false
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
         log.debug("New user of type: " + user.accept(new User.Visitor<User.UserType>() {
            @Override
            public User.UserType visitTeacher(Teacher t) {
               return User.UserType.TEACHER;
            }

            @Override
            public User.UserType visitStudent(Student s) {
               return User.UserType.STUDENT;
            }
         }));
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
    *
     pre: !userlist.containsKey(username) && loggedIn == false

     post: userlist.containsKey(username) && loggedIn == false
    */

   public static boolean register(String username, String pass, String first, String last, User.UserType type) {
      User user = null;
      populateUserList();
      if (!userlist.containsKey(username)) {
         switch (type) {
            case TEACHER:
               user = new Teacher(username, first, last, pass);
               userlist.put(username, user);
               registerUser(username, user);
               break;
            case STUDENT:
               user = new Student(username, first, last, pass);
               userlist.put(username, user);
               registerUser(username, user);
         }
         return true;
      }
      return false;
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
   public static boolean endUserSession(){
      loggedIn = false;
      loggedInUser = null;
      return true;
   }

   private static void populateUserList(){

      String filename = USER_FILE, line, username, firstName, lastName, password, type;;

      Scanner fileScan, lineScanner;
      boolean isAdmin;
      File file = new File(filename);

      try{

         if (!file.createNewFile()) {
            log.debug("File already made");
         }

         fileScan = new Scanner(file);

         while(fileScan.hasNextLine()) {
            line = fileScan.nextLine();

            if(!line.equals("")) {
               User newUser = User.fromString(line);
               userlist.put(newUser.getUsername(), newUser);
            }

         }
      }
      catch(IOException e){
         e.printStackTrace();
      }


   }

   private static void registerUser(String username, User user){

      if(!username.equals("tester")) {
         String output = user.accept(new User.Visitor<String>() {
            @Override
            public String visitTeacher(Teacher t) {
               return User.UserType.TEACHER.name();
            }

            @Override
            public String visitStudent(Student s) {
               return User.UserType.STUDENT.name();
            }
         });
         log.debug("{} being registered", output);
         output = output + " " + username + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getPassword() + "\n";

         try {
            Files.write(Paths.get(USER_FILE), output.getBytes(), StandardOpenOption.APPEND);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
}
