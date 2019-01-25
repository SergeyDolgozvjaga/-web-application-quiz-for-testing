package com.sergeydolgozvjaga.petprojet.model;

import com.sergeydolgozvjaga.petproject.model.TestResult;
import com.sergeydolgozvjaga.petproject.model.TestSector;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

public class TestResultTest {

    private H2Connector h2Connector = new H2Connector();
    private TestResult testResult;
    private TestSector testSector;

    @Before
    public void init() throws ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();
        testResult = new TestResult();
    }

    @After
    public void destroy() throws DatabaseException, LockException {
        h2Connector.dropDatabase();
    }

    @Test
    public void testResultTest(){
        //GIVEN
        int expectedResultId = 1;
        int expectedTestId = 1;
        int expectedUserId = 1;
        int expectedResultPoints = 10;
        boolean expectedIsTestDone = true;
        String expectedSubject = "Science";
        String expectedName = "Space";
        int expectedDifficultLevel = 2;
        int expectedSolveTime = 10;
        //WHEN
        testResult.setResultID(1);
        testResult.setUserID(1);
        testResult.setTestID(1);
        testResult.setIsTestDone(true);
        testResult.setResultPoints(10);

        testSector = new TestSector();
        testSector.setSubject("Science");
        testSector.setName("Space");
        testSector.setDifficultLevel(2);
        testSector.setSolveTime(10);
        testResult.setTestSector(testSector);
        //THEN
        Assert.assertNotNull(testResult);
        Assert.assertEquals(expectedResultId, testResult.getResultID());
        Assert.assertEquals(expectedTestId, testResult.getTestID());
        Assert.assertEquals(expectedUserId, testResult.getUserID());
        Assert.assertEquals(expectedResultPoints, testResult.getResultPoints());
        Assert.assertEquals(expectedIsTestDone, testResult.getIsTestDone());
        Assert.assertNotNull(testSector);
        Assert.assertEquals(expectedSubject, testResult.getTestSector().getSubject());
        Assert.assertEquals(expectedName, testResult.getTestSector().getName());
        Assert.assertEquals(expectedDifficultLevel, testResult.getTestSector().getDifficultLevel());
        Assert.assertEquals(expectedSolveTime, testResult.getTestSector().getSolveTime());
    }
}
