package com.sergeydolgozvjaga.petprojet.model;

import com.sergeydolgozvjaga.petproject.model.TestAnswer;
import com.sergeydolgozvjaga.petproject.model.TestQuestion;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

import java.util.ArrayList;

public class TestQuestionTest {

    private H2Connector h2Connector = new H2Connector();
    private TestQuestion testQuestion;


    @Before
    public void init() throws ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();
        testQuestion = new TestQuestion();
    }

    @After
    public void destroy() throws DatabaseException, LockException {
        h2Connector.dropDatabase();
    }

    @Test
    public void testQuestionTest(){
        //GIVEN
        int expectedTestId = 1;
        int expectedQuestionId = 1;
        String expectedQuestion = "Simple question";
        int expectedTestAnswersSize = 1;
        //WHEN
        testQuestion.setQuestionID(1);
        testQuestion.setTestID(1);
        testQuestion.setQuestion("Simple question");
        TestAnswer testAnswer = new TestAnswer();
        ArrayList<TestAnswer> testAnswers = new ArrayList<>();
        testAnswers.add(0, testAnswer);
        testQuestion.setTestAnswers(testAnswers);
        //THEN
        Assert.assertNotNull(testQuestion);
        Assert.assertEquals(expectedTestId, testQuestion.getTestID());
        Assert.assertEquals(expectedQuestion, testQuestion.getQuestion());
        Assert.assertEquals(expectedQuestionId, testQuestion.getQuestionID());
        Assert.assertEquals(expectedTestAnswersSize, testQuestion.getTestAnswers().size());
    }
}
