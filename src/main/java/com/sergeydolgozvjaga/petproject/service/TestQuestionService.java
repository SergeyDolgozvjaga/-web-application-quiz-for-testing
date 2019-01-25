package com.sergeydolgozvjaga.petproject.service;

import com.sergeydolgozvjaga.petproject.dao.TestAnswerDao;
import com.sergeydolgozvjaga.petproject.dao.TestQuestionDao;
import com.sergeydolgozvjaga.petproject.model.TestAnswer;
import com.sergeydolgozvjaga.petproject.model.TestQuestion;

import java.util.List;

/**
 * Service for questions to test
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class TestQuestionService {

    private TestQuestion testQuestion;
    private TestQuestionDao testQuestionDao;
    private TestAnswerDao testAnswerDao;


    public List<TestQuestion> getAllTestQuestions() {
        testQuestionDao = new TestQuestionDao();
        try {
            return testQuestionDao.getAll();
        } finally {
            testQuestionDao.closeConnection();
        }
    }

    public TestQuestion getTestQuestion(int questionID) {
        testQuestionDao = new TestQuestionDao();
        try {
            return testQuestionDao.getEntityById(questionID);
        } finally {
            testQuestionDao.closeConnection();
        }
    }

    public List<TestQuestion> getQuestionsByTestID(int id) {
        testQuestionDao = new TestQuestionDao();
        List<TestQuestion> testQuestions;
        try {
            testQuestions = testQuestionDao.getQuestionsByTestId(id);
            return testQuestions;
        } finally {
            testQuestionDao.closeConnection();
        }

    }

    public TestQuestion createTestQuestion(int testID, String question) {
        testQuestionDao = new TestQuestionDao();
        try {
            testQuestion = new TestQuestion();
            testQuestion.setTestID(testID);
            testQuestion.setQuestion(question);
            testQuestionDao.create(testQuestion);
            return testQuestion;
        } finally {
            testQuestionDao.closeConnection();
        }
    }

    public TestQuestion updateTestQuestion(int questionID, int testID, String question) {
        testQuestionDao = new TestQuestionDao();
        try {
            testQuestion = new TestQuestion();
            testQuestion.setQuestionID(questionID);
            testQuestion.setTestID(testID);
            testQuestion.setQuestion(question);
            testQuestionDao.update(testQuestion);
            return testQuestion;
        } finally {
            testQuestionDao.closeConnection();
        }
    }

    public void deleteTestQuestion(int questionID) {
        testQuestionDao = new TestQuestionDao();
        try {
            testQuestionDao.delete(questionID);
        } finally {
            testQuestionDao.closeConnection();
        }
    }

    public Integer getQuestionsSize(int testID) {
        testQuestionDao = new TestQuestionDao();
        try {
            return testQuestionDao.getQuestionsByTestId(testID).size();
        } finally {
            testQuestionDao.closeConnection();
        }
    }


    public void addAnswersToQuestion(TestQuestion testQuestion) {
        testAnswerDao = new TestAnswerDao();
        try {
            List<TestAnswer> answers = testAnswerDao.getAnswersByQuestionId(testQuestion
                    .getQuestionID());
            if (answers.isEmpty()) {
                return;
            } else {
                testQuestion.setTestAnswers(answers);
            }
        } finally {
            testAnswerDao.closeConnection();
        }
    }
}
