package com.sergeydolgozvjaga.petproject.dao;

import com.sergeydolgozvjaga.petproject.model.TestQuestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Realization DAO pattern for TestQuestion
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class TestQuestionDao extends AbstractDao<TestQuestion, Integer> {

    static final Logger LOG = LoggerFactory.getLogger(TestQuestionDao.class);
    private ResultSet resultSet;


    public TestQuestionDao() {}


    @Override
    public List<TestQuestion> getAll() {
        ArrayList<TestQuestion> questions = new ArrayList<>();

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTQUESTION_GET_ALL.query())){
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TestQuestion testQuestion = new TestQuestion();
                testQuestion.setQuestionID(resultSet.getInt(1));
                testQuestion.setTestID(resultSet.getInt(2));
                testQuestion.setQuestion(resultSet.getString(3));
                questions.add(testQuestion);
            }
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return questions;
    }

    @Override
    public TestQuestion getEntityById(Integer id) {
        TestQuestion testQuestion = new TestQuestion();

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTQUESTION_GET_BY_ID.query())) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                testQuestion.setQuestionID(resultSet.getInt(1));
                testQuestion.setTestID(resultSet.getInt(2));
                testQuestion.setQuestion(resultSet.getString(3));
            }
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return testQuestion;
    }

    @Override
    public boolean create(TestQuestion entity) {
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTQUESTION_CREATE.query())) {
            preparedStatement.setInt(1, entity.getTestID());
            preparedStatement.setString(2, entity.getQuestion());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return true;
    }

    @Override
    public TestQuestion update(TestQuestion entity) {
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTQUESTION_UPDATE.query())){
            preparedStatement.setInt(1, entity.getTestID());
            preparedStatement.setString(2, entity.getQuestion());
            preparedStatement.setInt(3, entity.getQuestionID());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return entity;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTQUESTION_DELETE_BY_ID.query())) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return true;
    }

    public ArrayList<TestQuestion> getQuestionsByTestId(Integer testID) {
        ArrayList<TestQuestion> questions = new ArrayList<>();

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTQUESTION_GET_BY_TESTID.query())) {
            preparedStatement.setInt(1, testID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TestQuestion testQuestion = new TestQuestion();
                testQuestion.setQuestionID(resultSet.getInt(1));
                testQuestion.setQuestion(resultSet.getString(2));
                testQuestion.setTestID(testID);
                questions.add(testQuestion);
            }
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return questions;
    }
}
