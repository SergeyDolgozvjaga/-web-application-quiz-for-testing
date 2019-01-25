package com.sergeydolgozvjaga.petprojet.service;

import com.sergeydolgozvjaga.petproject.dao.TestAnswerDao;
import com.sergeydolgozvjaga.petproject.dao.TestQuestionDao;
import com.sergeydolgozvjaga.petproject.dao.TestSectorDao;
import com.sergeydolgozvjaga.petproject.model.TestAnswer;
import com.sergeydolgozvjaga.petproject.model.TestQuestion;
import com.sergeydolgozvjaga.petproject.model.TestSector;
import com.sergeydolgozvjaga.petproject.service.TestAnswerService;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

public class TestAnswerServiceTest {

    private H2Connector h2Connector = new H2Connector();
    private TestAnswerDao testAnswerDao;
    private TestAnswerService testAnswerService;
    private TestAnswer testAnswer;
    private TestSector testSector;
    private TestSectorDao testSectorDao;
    private TestQuestion testQuestion;
    private TestQuestionDao testQuestionDao;

    @Before
    public void init() throws  ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();

        testAnswerService = new TestAnswerService();
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
    public void deleteTestAnswerTest(){
        //GIVEN
        int expectedSize = 0;
        //WHEN
        testAnswerDao.create(testAnswer);
        testAnswerDao.delete(1);
        //THEN
        Assert.assertNotNull(testAnswerService);
        Assert.assertEquals(expectedSize, testAnswerService.getTestAnswer(1).getAnswerID());
    }


}
