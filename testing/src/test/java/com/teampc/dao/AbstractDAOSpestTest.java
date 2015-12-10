package com.teampc.dao;

import com.google.common.collect.Lists;
import com.rits.cloning.Cloner;
import lombok.Data;
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

import static java.util.stream.Collectors.toList;

@RunWith(SpestRunner.class)
public class AbstractDAOSpestTest
{
    @Before
    public void setUp()
    {
        testObj = new AbstractDAO<TestHasId>() {
           @Override
           protected Class<TestHasId> getEntityClass() {
              return TestHasId.class;
           }
        };

    }

    /*Start generated tests*/
    private Class clazz = com.teampc.dao.AbstractDAO.class;

    private Cloner cloner = new Cloner();
    private com.teampc.dao.AbstractDAO<TestHasId> testObj;

    @Data
    private static class TestHasId implements HasId {
       private int id;
    }

    @Test
    public void insertTest_0() throws Exception
    {
        int testComboIndex;

        List<TestHasId> testPoints_0 = IntStream.range(1, 5).mapToObj(x -> new TestHasId()).collect(toList());
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

        TestHasId param_0;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);

            testObj.insert(param_0);
            Assert.assertTrue(testObj.fetchAll().contains(param_0));
            setUp();
        }
    }

    @Test
    public void insertTest_1() throws Exception
    {
        int testComboIndex;

        List<java.util.Collection<TestHasId>> testPoints_0 = IntStream.range(1, 5).mapToObj(x -> Lists.newArrayList(new TestHasId(), new TestHasId(), new TestHasId())).collect(Collectors.toList());
        int[][] combinations = CombinationSupport.getCombinations(testPoints_0.size());

        java.util.Collection<TestHasId> param_0;
        for(testComboIndex = 0; testComboIndex < combinations.length; testComboIndex++)
        {
            param_0 = testPoints_0.get(combinations[testComboIndex][0]);

            testObj.insert(param_0);
            Assert.assertTrue(testObj.fetchAll().containsAll(param_0));

            setUp();
        }
    }
    /*End generated tests*/
}
