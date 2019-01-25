package com.sergeydolgozvjaga.petprojet.dao;

import com.sergeydolgozvjaga.petproject.dao.TestResultDao;
import com.sergeydolgozvjaga.petproject.model.TestResult;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

import java.util.ArrayList;

public class TestResultDaoTest {

    private H2Connector h2Connector = new H2Connector();
    private TestResultDao testResultDao;
    private TestResult testResult;

    @Before
    public void init() throws ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();
        testResultDao = new TestResultDao();
        testResult = new TestResult();
        testResult.setUserID(1);
        testResult.setTestID(1);
        testResult.setIsTestDone(true);
        testResult.setResultPoints(10);
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
        testResult.setResultPoints(1);
        testResultDao.create(testResult);
        //THEN
        Assert.assertNotNull(testResult);
        Assert.assertEquals(expectedSize, testResultDao.getAll().size());
    }

    @Test
    public void deleteTest() {
        //GIVEN
        int expectedSize = 0;
        int expectedFalseSize = 1;
        //WHEN
        testResultDao.create(testResult);
        testResultDao.delete(1);
        //THEN
        Assert.assertNotNull(testResultDao);
        Assert.assertNotEquals(expectedFalseSize, testResultDao.getAll().size());
        Assert.assertEquals(expectedSize, testResultDao.getAll().size());
    }

    @Test
    public void getResultsByUserIdTest() {
        //GIVEN
        int expectedSize = 0;
        //WHEN
        ArrayList<TestResult> results = testResultDao.getResultsByUserId(1);
        //THEN
        Assert.assertNotNull(testResultDao);
        Assert.assertEquals(expectedSize, results.size());
    }
}
