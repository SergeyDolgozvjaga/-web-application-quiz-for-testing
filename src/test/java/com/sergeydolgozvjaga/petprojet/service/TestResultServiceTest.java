package com.sergeydolgozvjaga.petprojet.service;

import com.sergeydolgozvjaga.petproject.dao.TestResultDao;
import com.sergeydolgozvjaga.petproject.model.TestResult;
import com.sergeydolgozvjaga.petproject.service.TestResultService;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

public class TestResultServiceTest {

    private H2Connector h2Connector = new H2Connector();
    private TestResultService testResultService;
    private TestResultDao testResultDao;
    private TestResult testResult;


    @Before
    public void init() throws ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();
        testResultService = new TestResultService();
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
    public void deleteTestResultTest() {
        //GIVEN
        int expectedUserID =  0;
        int expectedTestID =  0;
        boolean expectedIsTestDone = false;
        int expectedResultPoints = 0;
        //WHEN
        testResultDao.create(testResult);
        testResultService.deleteTestResult(1);
        //THEN
        Assert.assertNotNull(testResultService);
        Assert.assertEquals(expectedUserID, testResultService.getTestResult(1).getUserID());
        Assert.assertEquals(expectedTestID, testResultService.getTestResult(1).getTestID());
        Assert.assertEquals(expectedIsTestDone, testResultService.getTestResult(1).getIsTestDone());
        Assert.assertEquals(expectedResultPoints, testResultService.getTestResult(1).getResultPoints());
    }


}
