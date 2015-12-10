package com.teampc.model.admin.access;

import com.teampc.model.admin.Student;
import com.teampc.model.admin.Teacher;
import com.teampc.model.admin.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Greg on 12/9/15.
 */
public class UserSessionTest {

   @Test
   public void testLogin() {

      assertEquals(UserSession.login("gsawers", "abc123"), true);
      assertEquals(UserSession.loggedIn, true);
      UserSession.endUserSession();

      assertEquals(UserSession.login("gsawers", "guest1234"), false);
      assertEquals(UserSession.loggedIn, false);
   }

   @Test
   public void testEndUserSession(){
      UserSession.login("gsawers", "abc123");
      assertEquals(UserSession.loggedIn, true);
      assertEquals(UserSession.endUserSession(), true);
      assertEquals(UserSession.loggedIn, false);
      assertEquals(UserSession.loggedInUser, null);
   }

   @Test
   public void testRegister(){
      UserSession.endUserSession();
      assertEquals(UserSession.getUserlist().containsKey("tester"), false);
      assertEquals(UserSession.loggedIn, false);
      assertTrue(UserSession.register("tester", "test", "test", "test",  User.UserType.TEACHER).isPresent());
      assertEquals(UserSession.getUserlist().containsKey("tester"), true);
      assertEquals(UserSession.loggedIn, false);

   }

   @Test(expected = IllegalArgumentException.class)
   public void testThrowsException() {
      User.fromString("ERROR user first last pass");
   }
}
