package com.sergeydolgozvjaga.petprojet.dao;

import com.sergeydolgozvjaga.petproject.dao.TestSectorDao;
import com.sergeydolgozvjaga.petproject.model.TestSector;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

public class TestSectorDaoTest {

    private H2Connector h2Connector = new H2Connector();
    private TestSector testSector;
    private TestSectorDao testSectorDao;


    @Before
    public void init() throws ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();
        testSectorDao =  new TestSectorDao();
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
    public void getAllTest(){
        //GIVEN
        int expectedSize = 1;
        //WHEN
        testSector.setSubject("Military");
        testSectorDao.create(testSector);
        //THEN
        Assert.assertNotNull(testSector);
        Assert.assertEquals(expectedSize, testSectorDao.getAll().size());
    }

    @Test
    public void getEntityByIdTest(){
        //GIVEN
        String expectedSubject = "Military";
        String expectedName = "Pentagon";
        int expectedDifficultLevel = 1;
        int expectedSolveTime = 15;
        //WHEN
        testSector.setSubject("Military");
        testSector.setName("Pentagon");
        testSector.setDifficultLevel(1);
        testSector.setSolveTime(15);
        testSectorDao.create(testSector);
        //THEN
        Assert.assertNotNull(testSectorDao);
        Assert.assertEquals(expectedSubject, testSectorDao.getEntityById(1).getSubject());
        Assert.assertEquals(expectedName, testSectorDao.getEntityById(1).getName());
        Assert.assertEquals(expectedDifficultLevel, testSectorDao.getEntityById(1).getDifficultLevel());
        Assert.assertEquals(expectedSolveTime, testSectorDao.getEntityById(1).getSolveTime());
    }

    @Test
    public void createTest() {
        //GIVEN
        String expectedSubject = "Military";
        String expectedName = "Pentagon";
        int expectedDifficultLevel = 1;
        int expectedSolveTime = 15;
        int expectedSize = 1;
        //WHEN
        testSector.setSubject("Military");
        testSector.setName("Pentagon");
        testSector.setDifficultLevel(1);
        testSector.setSolveTime(15);
        testSectorDao.create(testSector);
        //THEN
        Assert.assertNotNull(testSectorDao);
        Assert.assertEquals(expectedSubject, testSectorDao.getEntityById(1).getSubject());
        Assert.assertEquals(expectedName, testSectorDao.getEntityById(1).getName());
        Assert.assertEquals(expectedDifficultLevel, testSectorDao.getEntityById(1).getDifficultLevel());
        Assert.assertEquals(expectedSolveTime, testSectorDao.getEntityById(1).getSolveTime());
        Assert.assertEquals(expectedSize, testSectorDao.getAll().size());
    }

    @Test
    public void updateTest() {
        //GIVEN
        String expectedSubject = "Military";
        String expectedName = "Pentagon";
        int expectedDifficultLevel = 1;
        int expectedSolveTime = 15;
        int expectedSize = 1;
        //WHEN
        testSectorDao.create(testSector);
        testSector.setTestID(1);
        testSector.setSubject("Military");
        testSector.setName("Pentagon");
        testSector.setDifficultLevel(1);
        testSector.setSolveTime(15);
        testSectorDao.update(testSector);
        //THEN
        Assert.assertNotNull(testSectorDao);
        Assert.assertEquals(expectedSubject, testSectorDao.getEntityById(1).getSubject());
        Assert.assertEquals(expectedName, testSectorDao.getEntityById(1).getName());
        Assert.assertEquals(expectedDifficultLevel, testSectorDao.getEntityById(1).getDifficultLevel());
        Assert.assertEquals(expectedSolveTime, testSectorDao.getEntityById(1).getSolveTime());
        Assert.assertEquals(expectedSize, testSectorDao.getAll().size());
    }

    @Test
    public void deleteTest(){
        //GIVEN
        int expectedSize = 1;
        String expectedSubject = "Military";
        String expectedName = "Pentagon";
        int expectedDifficultLevel = 1;
        int expectedSolveTime = 15;
        //WHEN
        testSector.setTestID(1);
        testSector.setSubject("Military");
        testSector.setName("Pentagon");
        testSector.setDifficultLevel(1);
        testSector.setSolveTime(15);
        testSectorDao.create(testSector);
        testSector.setTestID(2);
        testSector.setSubject("Freedom");
        testSector.setName("Independence");
        testSector.setDifficultLevel(1);
        testSector.setSolveTime(15);
        testSectorDao.create(testSector);
        testSectorDao.delete(2);
        //THEN
        Assert.assertNotNull(testSectorDao);
        Assert.assertEquals(expectedSubject, testSectorDao.getEntityById(1).getSubject());
        Assert.assertEquals(expectedName, testSectorDao.getEntityById(1).getName());
        Assert.assertEquals(expectedDifficultLevel, testSectorDao.getEntityById(1).getDifficultLevel());
        Assert.assertEquals(expectedSolveTime, testSectorDao.getEntityById(1).getSolveTime());
        Assert.assertEquals(expectedSize, testSectorDao.getAll().size());
        Assert.assertEquals(expectedSize, testSectorDao.getAll().size());
    }
}
