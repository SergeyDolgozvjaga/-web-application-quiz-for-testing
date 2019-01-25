package com.sergeydolgozvjaga.petprojet.model;

import com.sergeydolgozvjaga.petproject.model.TestAnswer;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

public class TestAnswerTest {

    private H2Connector h2Connector = new H2Connector();
    private TestAnswer testAnswer;


    @Before
    public void init() throws  ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();
    }

    @After
    public void destroy() throws DatabaseException, LockException {
        h2Connector.dropDatabase();
    }

    @Test
    public void testAnswerTest(){
        //GIVEN
        int expectedQuestionId = 1;
        int expectedAnswerId = 1;
        String expectedAnswerTheQuestion = "Yes";
        boolean expectedIsAnswerCorrect = true;
        //WHEN
        testAnswer = new TestAnswer();
        testAnswer.setAnswerID(1);
        testAnswer.setQuestionID(1);
        testAnswer.setAnswerTheQuestion("Yes");
        testAnswer.setAnswerCorrect(true);
        //THEN
        Assert.assertNotNull(testAnswer);
        Assert.assertEquals(expectedAnswerTheQuestion, testAnswer.getAnswerTheQuestion());
        Assert.assertEquals(expectedQuestionId, testAnswer.getQuestionID());
        Assert.assertEquals(expectedAnswerId, testAnswer.getAnswerID());
        Assert.assertEquals(expectedIsAnswerCorrect, testAnswer.getIsAnswerCorrect());
    }
}
