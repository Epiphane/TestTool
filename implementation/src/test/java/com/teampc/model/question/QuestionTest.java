package com.teampc.model.question;

import com.google.common.collect.Lists;
import com.teampc.model.testtaking.MultipleChoiceQuestionResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testing.CombinationSupport;
import testing.JavaTestUtility;
import testing.runner.SpestRunner;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpestRunner.class)
public class QuestionTest
{
    @Before
    public void setUp()
    {
        testObj = new Question<>(0, "Why?", 2, new MultipleChoiceQuestionResponse(1, Lists.newArrayList("Because", "42", "Yes")), Question.QuestionType.MULTIPLE_CHOICE);

    }

    /*Start generated tests*/

    private File rootDirectory = new File(".");
    private File sourceFile = new File("src/main/java/com/teampc/model/question/Question.java");
    private JavaTestUtility javaTestUtility = new JavaTestUtility(rootDirectory, sourceFile, false);
    private com.teampc.model.question.Question testObj;
    @Test
    public void gradeTest_0() throws Exception
    {
        int testComboIndex;

        List<MultipleChoiceQuestionResponse> testPoints_0 = IntStream.range(1,5).mapToObj(answer -> new MultipleChoiceQuestionResponse(answer, Lists.newArrayList("1", "2", "3", "4"))).collect(Collectors.toList());
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

        com.teampc.model.testtaking.QuestionResponse param_0;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);

            testObj.grade(param_0);
            Assert.assertTrue(param_0.getPointsReceived() >= 0);
            setUp();
        }
    }
    /*End generated tests*/
}
