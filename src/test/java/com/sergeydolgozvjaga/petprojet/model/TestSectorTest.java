package com.sergeydolgozvjaga.petprojet.model;

import com.sergeydolgozvjaga.petproject.model.TestSector;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

public class TestSectorTest {

    private H2Connector h2Connector = new H2Connector();
    private TestSector testSector;


    @Before
    public void init() throws ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();
        testSector = new TestSector();
    }

    @After
    public void destroy() throws DatabaseException, LockException {
        h2Connector.dropDatabase();
    }

    @Test
    public void testSectorTest(){
        //GIVEN
        String expectedSubject = "Science";
        String expectedName = "Space";
        int expectedDifficultLevel = 2;
        int expectedSolveTime = 10;
        //WHEN
        testSector.setSubject("Science");
        testSector.setName("Space");
        testSector.setDifficultLevel(2);
        testSector.setSolveTime(10);
        //THEN
        Assert.assertNotNull(testSector);
        Assert.assertEquals(expectedSubject, testSector.getSubject());
        Assert.assertEquals(expectedName, testSector.getName());
        Assert.assertEquals(expectedDifficultLevel, testSector.getDifficultLevel());
        Assert.assertEquals(expectedSolveTime, testSector.getSolveTime());
    }
}
