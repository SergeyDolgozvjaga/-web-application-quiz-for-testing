package com.sergeydolgozvjaga.petprojet.dao;

import com.sergeydolgozvjaga.petproject.dao.TestQuestionDao;
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

public class TestQuestionDaoTest {

    private H2Connector h2Connector = new H2Connector();
    private TestQuestion testQuestion;
    private TestQuestionDao testQuestionDao;

    @Before
    public void init() throws ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();

        testQuestionDao = new TestQuestionDao();
        testQuestion = new TestQuestion();
        testQuestion.setTestID(1);
        testQuestion.setQuestion("First question");
    }

    @After
    public void destroy() throws DatabaseException, LockException {
        h2Connector.dropDatabase();
    }


    @Test
    public void getQuestionsByTestIdTest() {
        //GIVEN
        int expectedSize = 0;
        //WHEN
        ArrayList<TestQuestion> results = testQuestionDao.getQuestionsByTestId(1);
        //THEN
        Assert.assertNotNull(testQuestionDao);
        Assert.assertEquals(expectedSize, results.size());
    }
}
