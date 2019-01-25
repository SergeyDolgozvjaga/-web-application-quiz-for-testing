package com.sergeydolgozvjaga.petproject.service;

import com.sergeydolgozvjaga.petproject.dao.TestResultDao;
import com.sergeydolgozvjaga.petproject.model.TestResult;

import java.util.List;

/**
 * Service for count results of testing
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class ResultCountService {

    private TestResultDao testResultDao;
    private double correctAnswerPercent = 0;


    public List<TestResult> getTestResults() {
        testResultDao = new TestResultDao();
        try {
            return testResultDao.getAll();
        } finally {
            testResultDao.closeConnection();
        }
    }

    public double getPercentAmount(int answerSize, int correctAnswerSize, int correctValue) {

        if (answerSize >= correctAnswerSize) {
            correctAnswerPercent = Math.round(Double.valueOf(correctValue) / Double.valueOf(answerSize) * 100);
        } else {
            correctAnswerPercent = Math.round(Double.valueOf(correctValue) / Double.valueOf(correctAnswerSize) * 100);
        }
        return correctAnswerPercent;
    }

    public void setResult(int userID, int testID, double percent, int resultPoints) {
        testResultDao = new TestResultDao();
        try {
            TestResult testResult = new TestResult();
            testResult.setUserID(userID);
            testResult.setTestID(testID);
            testResult.setIsTestDone(isPassed(percent));
            testResult.setResultPoints(resultPoints);
            testResultDao.create(testResult);
        } finally {
            testResultDao.closeConnection();
        }
    }

    private boolean isPassed(double percent) {
        return percent > 40;
    }
}
