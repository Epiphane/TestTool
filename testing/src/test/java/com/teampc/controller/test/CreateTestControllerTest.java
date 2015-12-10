package com.teampc.controller.test;

import com.google.common.base.Strings;
import com.teampc.model.question.Question;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class CreateTestControllerTest {

   @Test
   public void submitTestTest() {
      CreateTestController controller = new CreateTestController();

      com.teampc.model.test.Test newTest = controller.testMe();
      assertTrue(!Strings.isNullOrEmpty(newTest.getName()) && !Strings.isNullOrEmpty(newTest.getCourseName()) &&
         newTest.getStartDate() != null && newTest.getEndDate() != null);

      com.teampc.model.test.Test newTestNoDate = controller.testMeNoDate();
      assertTrue(!Strings.isNullOrEmpty(newTestNoDate.getName()) && !Strings.isNullOrEmpty(newTestNoDate.getCourseName()));
   }

   @Test
   public void generateQuestionsTest() {
      CreateTestController controller = new CreateTestController();

      Collection<Question> questionList = controller.testMeGenerateQuestions(0);
      assertTrue(questionList.size() == 0);

      Collection<Question> questionList1 = controller.testMeGenerateQuestions(50);
      assertTrue(questionList1.size() <= 50);
   }
}
