package com.sergeydolgozvjaga.petproject.service;

import com.sergeydolgozvjaga.petproject.dao.TestSectorDao;
import com.sergeydolgozvjaga.petproject.model.TestSector;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

/**
 * Service for test module
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class TestSectorService {

    private TestSector testSector;
    private TestSectorDao testSectorDao;
    private TestQuestionService testQuestionService;

    public List<TestSector> getAllTestSectors() {
        testSectorDao = new TestSectorDao();
        try {
            return testSectorDao.getAll();
        } finally {
            testSectorDao.closeConnection();
        }
    }

    public TestSector getTestSector(int testID) {
        testSectorDao = new TestSectorDao();
        try {
            return testSectorDao.getEntityById(testID);
        } finally {
            testSectorDao.closeConnection();
        }
    }

    public void createTestSector(String testSubject, String testName, int testDifficultLevel,  int solveTime) throws SQLException {
        testSectorDao = new TestSectorDao();
        try {
            testSector = new TestSector();
            testSector.setSubject(testSubject);
            testSector.setName(testName);
            testSector.setDifficultLevel(testDifficultLevel);
            testSector.setSolveTime(solveTime);
            testSectorDao.create(testSector);
        } finally {
            testSectorDao.closeConnection();
        }
    }

    public void updateTestSector(int testID, String testSubject, String testName, int testDifficultLevel,  int solveTime) {
        testSectorDao = new TestSectorDao();
        try {
            TestSector testSector = new TestSector();
            testSector.setTestID(testID);
            testSector.setSubject(testSubject);
            testSector.setName(testName);
            testSector.setDifficultLevel(testDifficultLevel);
            testSector.setSolveTime(solveTime);
            testSectorDao.update(testSector);
        } finally {
            testSectorDao.closeConnection();
        }
    }

    public void deleteTestSector(int testID) {
        testSectorDao = new TestSectorDao();
        try {
            testSectorDao.delete(testID);
        } finally {
            testSectorDao.closeConnection();
        }
    }

    public void sortTestResults(List<TestSector> testSectors, String typeOfSort) {
        switch (typeOfSort) {
            case "subject":         subjectSort(testSectors);        break;
            case "name":            nameSort(testSectors);           break;
            case "difficult_level": difficultLevelSort(testSectors); break;
            case "questions":       questionsSort(testSectors);      break;
            default: throw new IllegalArgumentException();
        }
    }

    private void subjectSort(List<TestSector> testSectors){
        testSectors.sort(Comparator.comparing(z -> z.getSubject()));
    }

    private void nameSort(List<TestSector> testSectors){
        testSectors.sort(Comparator.comparing(z -> z.getName()));
    }

    private void difficultLevelSort(List<TestSector> testSectors){
        testSectors.sort(Comparator.comparing(z -> z.getDifficultLevel()));
    }

    private void questionsSort(List<TestSector> testSectors){
        testQuestionService = new TestQuestionService();
        testSectors.sort(Comparator.comparing(z -> testQuestionService.getQuestionsSize(z.getTestID())));
    }
}
