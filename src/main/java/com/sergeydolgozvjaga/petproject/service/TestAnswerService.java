package com.sergeydolgozvjaga.petproject.service;

import com.sergeydolgozvjaga.petproject.dao.TestAnswerDao;
import com.sergeydolgozvjaga.petproject.model.TestAnswer;

import java.util.List;

/**
 * Service for answer the test
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class TestAnswerService {

    private TestAnswer testAnswer;
    private TestAnswerDao testAnswerDao;

    public TestAnswer getTestAnswer(int answerID) {
        testAnswerDao = new TestAnswerDao();
        try {
            return testAnswerDao.getEntityById(answerID);
        } finally {
            testAnswerDao.closeConnection();
        }
    }

    public TestAnswer createTestAnswer(int questionID, String answerTheQuestion, boolean isAnswerCorrect) {
        testAnswerDao = new TestAnswerDao();
        try {
            testAnswer = new TestAnswer();
            testAnswer.setQuestionID(questionID);
            testAnswer.setAnswerTheQuestion(answerTheQuestion);
            testAnswer.setAnswerCorrect(isAnswerCorrect);
            testAnswerDao.create(testAnswer);
            return testAnswer;
        } finally {
            testAnswerDao.closeConnection();
        }
    }

    public TestAnswer updateTestAnswer(int answerID, int questionID, String answerTheQuestion, boolean isAnswerCorrect) {
        testAnswerDao = new TestAnswerDao();
        try {
            testAnswer = new TestAnswer();
            testAnswer.setAnswerID(answerID);
            testAnswer.setQuestionID(questionID);
            testAnswer.setAnswerTheQuestion(answerTheQuestion);
            testAnswer.setAnswerCorrect(isAnswerCorrect);
            testAnswerDao.update(testAnswer);
            return testAnswer;
        } finally {
            testAnswerDao.closeConnection();
        }
    }

    public void deleteTestAnswer(int answerID) {
        testAnswerDao = new TestAnswerDao();
        try {
            testAnswerDao.delete(answerID);
        } finally {
            testAnswerDao.closeConnection();
        }
    }

    public List<TestAnswer> getTestAnswersByQuestionID(int questionID) {
        testAnswerDao = new TestAnswerDao();
        try {
            return testAnswerDao.getAnswersByQuestionId(questionID);
        } finally {
            testAnswerDao.closeConnection();
        }
    }
}
