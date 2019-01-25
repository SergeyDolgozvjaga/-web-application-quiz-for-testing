package com.sergeydolgozvjaga.petprojet.service;

import com.sergeydolgozvjaga.petproject.dao.TestSectorDao;
import com.sergeydolgozvjaga.petproject.model.TestSector;
import com.sergeydolgozvjaga.petproject.service.TestSectorService;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

import java.sql.SQLException;
import java.util.List;

public class TestSectorServiceTest {

    private H2Connector h2Connector = new H2Connector();
    private TestSectorService testSectorService;
    private TestSectorDao testSectorDao;
    private TestSector testSector;

    @Before
    public void init() throws ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();
        testSectorDao =  new TestSectorDao();
        testSectorService = new TestSectorService();
        testSector = new TestSector();
        testSector.setSubject("Science");
        testSector.setName("Space");
        testSector.setDifficultLevel(2);
        testSector.setSolveTime(10);
    }

    @After
    public void destroy() throws DatabaseException, LockException {
        h2Connector.dropDatabase();
    }

    @Test
    public void getAllTestsTest() {
        //GIVEN
        int expectedSize = 1;
        //WHEN
        testSectorDao.create(testSector);
        List<TestSector> testSectors = testSectorService.getAllTestSectors();
        //THEN
        Assert.assertNotNull(testSectorService);
        Assert.assertEquals(expectedSize, testSectors.size());
    }

    @Test
    public void getTestSectorTest(){
        //GIVEN
        String expectedSubject = "Science";
        String expectedName= "Space";
        int expectedDifficultLevel = 2;
        int expectedSolveTime = 10;
        //WHEN
        testSectorDao.create(testSector);
        //THEN
        Assert.assertNotNull(testSectorService);
        Assert.assertEquals(expectedSubject, testSectorService.getTestSector(1).getSubject());
        Assert.assertEquals(expectedName, testSectorService.getTestSector(1).getName());
        Assert.assertEquals(expectedDifficultLevel, testSectorService.getTestSector(1).getDifficultLevel());
        Assert.assertEquals(expectedSolveTime, testSectorService.getTestSector(1).getSolveTime());
    }

    @Test
    public void createTestSectorTest() throws SQLException{
        //GIVEN
        String expectedSubject = "Marine";
        String expectedName= "Sea";
        int expectedDifficultLevel = 3;
        int expectedSolveTime = 15;
        //WHEN
        testSectorService.createTestSector("Marine", "Sea", 3, 15);
        //THEN
        Assert.assertNotNull(testSectorService);
        Assert.assertEquals(expectedSubject, testSectorService.getTestSector(1).getSubject());
        Assert.assertEquals(expectedName, testSectorService.getTestSector(1).getName());
        Assert.assertEquals(expectedDifficultLevel, testSectorService.getTestSector(1).getDifficultLevel());
        Assert.assertEquals(expectedSolveTime, testSectorService.getTestSector(1).getSolveTime());
    }

    @Test
    public void updateTestSectorTest(){
        //GIVEN
        String expectedSubject = "Water";
        String expectedName= "Lake";
        int expectedDifficultLevel = 2;
        int expectedSolveTime = 12;
        //WHEN
        testSectorDao.create(testSector);
        testSectorService.updateTestSector(1, "Water", "Lake", 2, 12);
        //THEN
        Assert.assertNotNull(testSectorService);
        Assert.assertEquals(expectedSubject, testSectorService.getTestSector(1).getSubject());
        Assert.assertEquals(expectedName, testSectorService.getTestSector(1).getName());
        Assert.assertEquals(expectedDifficultLevel, testSectorService.getTestSector(1).getDifficultLevel());
        Assert.assertEquals(expectedSolveTime, testSectorService.getTestSector(1).getSolveTime());
    }

    @Test
    public void deleteTestSectorTest(){
        //GIVEN
        int expectedSize = 1;
        String expectedSubject = "Science";
        String expectedName= "Space";
        int expectedDifficultLevel = 2;
        int expectedSolveTime = 10;
        //WHEN
        testSectorDao.create(testSector);
        TestSector testSector2 = new TestSector();
        testSector2.setTestID(2);
        testSector2.setSubject("Water");
        testSector2.setName("Lake");
        testSector2.setDifficultLevel(3);
        testSector2.setSolveTime(8);
        testSectorDao.create(testSector2);
        testSectorService.deleteTestSector(2);
        //THEN
        Assert.assertNotNull(testSectorService);

        Assert.assertEquals(expectedSubject, testSectorService.getTestSector(1).getSubject());
        Assert.assertEquals(expectedName, testSectorService.getTestSector(1).getName());
        Assert.assertEquals(expectedDifficultLevel, testSectorService.getTestSector(1).getDifficultLevel());
        Assert.assertEquals(expectedSolveTime, testSectorService.getTestSector(1).getSolveTime());
        Assert.assertEquals(expectedSize, testSectorService.getAllTestSectors().size());
    }


}
