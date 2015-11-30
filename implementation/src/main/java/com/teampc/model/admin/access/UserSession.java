package com.teampc.model.admin.access;

import com.teampc.model.admin.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Identifies the user that is currently logged into the application.
 * Users can have set restrictions, to prevent or allow access to parts
 * of the application
 */
public class UserSession {

   /** Current logged in user **/
   public static User loggedInUser;

   /**
    *
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
    * A session is defined by the user that is logged in
    */
   public  UserSession(User user) {

   }

   /**
    * @param username the username used to login.
    * @param password the password used to login
    *
    *
     pre: user != null
    *
     post: loggedIn == true && loggedInUser.equals(user)
    */
   public static boolean login(String username, String password){
      populateUserList();
      if(userlist.containsKey(username) && userlist.get(username).getPassword().equals(password))  {
         User user = userlist.get(username);
         loggedInUser = user;
         loggedIn = true;
         System.out.println("User gsawers logged in to TestTool.");
         return true;
      }
      else {
         System.out.println("Invalid username or password");
         return false;
      }
   }

   public static void Register(String username, String pass, String first, String last){
      User user = new User(username, first, last, pass);
      userlist.put(username, user);
      registerUser(username, user);
   }

   /**
    * Get the currently logged in user
    *
     pre: loggedIn && loggedInUser != null
    *
     post: loggedIn && loggedInUser != null
    */
   public static User getLoggedInUser(){

      System.out.println("gsawers is currently logged in");
      return new User();
   }

   /**
    * Ends the current user session
    */
   public static void endUserSession(){

   }

   private static void populateUserList(){
      String filename = "users.txt", line, username;
      Scanner fileScan = null, linescan;
      File file = new File(filename);

      try{

         if(file.createNewFile());
         else
            System.out.println("File already made");

         fileScan = new Scanner(file);

         while(fileScan.hasNextLine()) {
            line = fileScan.nextLine();
            linescan = new Scanner(line);
            username = linescan.next();
            User user = new User(username, linescan.next(), linescan.next(), linescan.next());
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
