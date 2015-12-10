package com.teampc.model.admin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by adufrene on 12/9/15.
 *
 */
public class UserTest {

   @Test
   public void testUserFromString() {
      String serializedUser = "STUDENT user first last pass";
      User fromString = User.fromString(serializedUser);

      // Test non-admin student
      assertEquals(new Student("user", "first", "last", "pass", false), fromString);

      serializedUser = "STUDENT user first last pass true";
      fromString = User.fromString(serializedUser);

      // Test admin student
      assertEquals(new Student("user", "first", "last", "pass", true), fromString);

      serializedUser = "TEACHER user first last pass";
      fromString = User.fromString(serializedUser);

      // Test non-admin teacher
      Teacher teacher = new Teacher("user", "first", "last", "pass", false);
      assertEquals(teacher, fromString);

      serializedUser = "TEACHER user first last pass true";
      fromString = User.fromString(serializedUser);

      // Test admin teacher
      assertEquals(new Teacher("user", "first", "last", "pass", true), fromString);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testThrowsException() {
      User.fromString("ERROR user first last pass");
   }
}
