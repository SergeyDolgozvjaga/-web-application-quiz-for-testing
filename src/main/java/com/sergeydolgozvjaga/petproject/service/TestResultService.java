package com.sergeydolgozvjaga.petproject.service;

import com.sergeydolgozvjaga.petproject.dao.TestResultDao;
import com.sergeydolgozvjaga.petproject.dao.TestSectorDao;
import com.sergeydolgozvjaga.petproject.model.TestResult;

import java.util.*;

/**
 * Service for results of test
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class TestResultService {

    private TestResult testResult;
    private TestResultDao resultDao;
    private TestSectorDao testSectorDao;
    private TestQuestionService testQuestionService;


    public List<TestResult> getAllTestResults() {
        resultDao = new TestResultDao();
        try {
            return resultDao.getAll();
        } finally {
            resultDao.closeConnection();
        }
    }

    public TestResult getTestResult(int resultID) {
        resultDao = new TestResultDao();
        try {
            return resultDao.getEntityById(resultID);
        } finally {
            resultDao.closeConnection();
        }
    }

    public void createTestResult(int userID, int testID) {
        resultDao = new TestResultDao();
        try {
            testResult = new TestResult();
            testResult.setUserID(userID);
            testResult.setTestID(testID);
            resultDao.create(testResult);
        } finally {
            resultDao.closeConnection();
        }
    }

    public boolean updateTestResult(int resultID, int resultPoints) {
        testResult = getTestResult(resultID);
        resultDao = new TestResultDao();
        try {
            testResult.setResultPoints(resultPoints);
            testResult.setIsTestDone(true);
            resultDao.update(testResult);
        } finally {
            resultDao.closeConnection();
        }
        return true;
    }

    public void deleteTestResult(int resultID) {
        resultDao = new TestResultDao();
        try {
            resultDao.delete(resultID);
        } finally {
            resultDao.closeConnection();
        }
    }

    public ArrayList<TestResult> takeSetOfResultsByUserId(int userID) {
        resultDao = new TestResultDao();
        testSectorDao = new TestSectorDao();
        ArrayList<TestResult> results = resultDao.getResultsByUserId(userID);
        for (TestResult result : results) {
            if (testSectorDao.getEntityById(result.getTestID()) != null) {
                result.setTestSector(testSectorDao.getEntityById(result.getTestID()));
            }
        }
        resultDao.closeConnection();
        testSectorDao.closeConnection();

        return results;
    }

    public void sortTestResults(ArrayList<TestResult> testResults, String typeOfSort) {
        switch (typeOfSort) {
            case "subject":         subjectSort(testResults);        break;
            case "name":            nameSort(testResults);           break;
            case "difficult_level": difficultLevelSort(testResults); break;
            case "questions":       questionsSort(testResults);      break;
            default:  throw new IllegalArgumentException();
        }
    }

    private void subjectSort(ArrayList<TestResult> testResults){
        testResults.sort(Comparator.comparing(z -> z.getTestSector().getSubject()));
    }

    private void nameSort(ArrayList<TestResult> testResults){
        testResults.sort(Comparator.comparing(z -> z.getTestSector().getName()));
    }

    private void difficultLevelSort(ArrayList<TestResult> testResults){
        testResults.sort(Comparator.comparing(z -> z.getTestSector().getDifficultLevel()));
    }

    private void questionsSort(ArrayList<TestResult> testResults){
        testQuestionService = new TestQuestionService();
        testResults.sort(Comparator.comparing(z -> testQuestionService.getQuestionsSize((z.getTestID()))));
    }
}
