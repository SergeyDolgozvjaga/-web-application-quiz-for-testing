package com.sergeydolgozvjaga.petprojet.service;

import com.sergeydolgozvjaga.petproject.dao.TestQuestionDao;
import com.sergeydolgozvjaga.petproject.model.TestQuestion;
import com.sergeydolgozvjaga.petproject.service.TestQuestionService;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

public class TestQuestionServiceTest {

    private H2Connector h2Connector = new H2Connector();
    private TestQuestionService testQuestionService;
    private TestQuestionDao testQuestionDao;
    private TestQuestion testQuestion;

    @Before
    public void init() throws ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();

        testQuestionService = new TestQuestionService();
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
    public void deleteTestQuestionTest(){
        //GIVEN
        int expectedSize = 0;
        //WHEN
        testQuestionDao.create(testQuestion);
        testQuestionService.deleteTestQuestion(1);
        //THEN
        Assert.assertNotNull(testQuestionService);
        Assert.assertEquals(expectedSize, testQuestionService.getTestQuestion(1).getQuestionID());
    }

}
