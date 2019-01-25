package com.sergeydolgozvjaga.petprojet.dao;

import com.sergeydolgozvjaga.petproject.dao.TestAnswerDao;
import com.sergeydolgozvjaga.petproject.model.TestAnswer;

import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

import java.util.List;

public class TestAnswerDaoTest {

    private H2Connector h2Connector = new H2Connector();
    private TestAnswerDao testAnswerDao;
    private TestAnswer testAnswer;

    @Before
    public void init() throws  ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();

        testAnswerDao = new TestAnswerDao();
        testAnswer = new TestAnswer();
        testAnswer.setQuestionID(1);
        testAnswer.setAnswerTheQuestion("Yes");
        testAnswer.setAnswerCorrect(true);
    }

    @After
    public void destroy() throws DatabaseException, LockException {
        h2Connector.dropDatabase();
    }

    @Test
    public void getAllTest(){
        //GIVEN
        int expectedSize = 0;
        //WHEN
        testAnswerDao.create(testAnswer);
        //THEN
        Assert.assertNotNull(testAnswerDao);
        Assert.assertEquals(expectedSize, testAnswerDao.getAll().size());
    }


    @Test
    public void deleteTest() {
        //GIVEN
        int expectedSize = 0;
        //WHEN
        testAnswerDao.create(testAnswer);
        testAnswerDao.delete(1);
        //THEN
        Assert.assertNotNull(testAnswerDao);
        Assert.assertEquals(expectedSize, testAnswerDao.getAll().size());
    }

    @Test
    public void getAnswersByQuestionIdTest() {
        //GIVEN
        int expectedSize = 0;
        //WHEN
        List<TestAnswer> results = testAnswerDao.getAnswersByQuestionId(1);
        //THEN
        Assert.assertNotNull(testAnswerDao);
        Assert.assertEquals(expectedSize, results.size());
    }
}
