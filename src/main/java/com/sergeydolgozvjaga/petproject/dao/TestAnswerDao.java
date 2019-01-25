package com.sergeydolgozvjaga.petproject.dao;

import com.sergeydolgozvjaga.petproject.model.TestAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Realization DAO pattern for TestAnswer
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class TestAnswerDao extends AbstractDao<TestAnswer, Integer> {

    static final Logger LOG = LoggerFactory.getLogger(TestAnswerDao.class);
    private ResultSet resultSet;

    public TestAnswerDao() {}


    @Override
    public List<TestAnswer> getAll() {
        ArrayList<TestAnswer> answers = new ArrayList<>();

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTANSWER_GET_ALL.query())) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TestAnswer testAnswer = new TestAnswer();
                testAnswer.setAnswerID(resultSet.getInt(1));
                testAnswer.setQuestionID(resultSet.getInt(2));
                testAnswer.setAnswerTheQuestion(resultSet.getString(3));
                testAnswer.setAnswerCorrect(resultSet.getBoolean(4));
                answers.add(testAnswer);
            }
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return answers;
    }

    @Override
    public TestAnswer getEntityById(Integer id) {
        TestAnswer testAnswer = new TestAnswer();

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTANSWER_GET_ENTITY_BY_ID.query())) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                testAnswer.setAnswerID(resultSet.getInt(1));
                testAnswer.setQuestionID(resultSet.getInt(2));
                testAnswer.setAnswerTheQuestion(resultSet.getString(3));
                testAnswer.setAnswerCorrect(resultSet.getBoolean(4));
            }
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return testAnswer;
    }

    @Override
    public boolean create(TestAnswer entity) {
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTANSWER_CREATE.query())) {
            preparedStatement.setInt(1, entity.getQuestionID());
            preparedStatement.setString(2, entity.getAnswerTheQuestion());
            preparedStatement.setBoolean(3, entity.getIsAnswerCorrect());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return true;
    }

    @Override
    public TestAnswer update(TestAnswer entity) {
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTANSWER_UPDATE.query())) {
            preparedStatement.setInt(1, entity.getQuestionID());
            preparedStatement.setString(2, entity.getAnswerTheQuestion());
            preparedStatement.setBoolean(3, entity.getIsAnswerCorrect());
            preparedStatement.setInt(4, entity.getAnswerID());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return entity;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTANSWER_DELETE_BY_ID.query())) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return true;
    }

    public List<TestAnswer> getAnswersByQuestionId(Integer questionID) {
        ArrayList<TestAnswer> answers = new ArrayList<>();

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTANSWER_GET_ANSWERS_BY_QUESTIONID.query())) {
            preparedStatement.setInt(1, questionID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TestAnswer testAnswer = new TestAnswer();
                testAnswer.setAnswerID(resultSet.getInt(1));
                testAnswer.setQuestionID(questionID);
                testAnswer.setAnswerTheQuestion(resultSet.getString(3));
                testAnswer.setAnswerCorrect(resultSet.getBoolean(4));
                answers.add(testAnswer);
            }
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return answers;
    }
}
