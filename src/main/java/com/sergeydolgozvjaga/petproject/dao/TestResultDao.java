package com.sergeydolgozvjaga.petproject.dao;

import com.sergeydolgozvjaga.petproject.model.TestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Realization DAO pattern for TestResult
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class TestResultDao extends AbstractDao<TestResult, Integer> {

    static final Logger LOG = LoggerFactory.getLogger(TestResultDao.class);
    private ResultSet resultSet;

    public TestResultDao() {}

    @Override
    public List<TestResult> getAll()  {
        ArrayList<TestResult> results = new ArrayList<>();

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTRESULT_GET_ALL.query())) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TestResult testResult = new TestResult();
                testResult.setResultID(resultSet.getInt(1));
                testResult.setUserID(resultSet.getInt(2));
                testResult.setTestID(resultSet.getInt(3));
                testResult.setIsTestDone(resultSet.getBoolean(4));
                testResult.setResultPoints(resultSet.getInt(5));
                results.add(testResult);
            }
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return results;
    }

    @Override
    public TestResult getEntityById(Integer id) {
        TestResult result = new TestResult();

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTRESULT_GET_ENTITY_BY_ID.query())){
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result.setResultID(id);
                result.setUserID(resultSet.getInt(2));
                result.setTestID(resultSet.getInt(3));
                result.setIsTestDone(resultSet.getBoolean(4));
                result.setResultPoints(resultSet.getInt(5));
            }
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return result;
    }

    @Override
    public boolean create(TestResult entity) {
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTRESULT_CREATE.query())) {
            preparedStatement.setInt(1, entity.getUserID());
            preparedStatement.setInt(2, entity.getTestID());
            preparedStatement.setBoolean(3, entity.getIsTestDone());
            preparedStatement.setInt(4, entity.getResultPoints());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return true;
    }

    @Override
    public TestResult update(TestResult entity) {
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTRESULT_UPDATE.query())) {
            preparedStatement.setInt(1, entity.getUserID());
            preparedStatement.setInt(2, entity.getTestID());
            preparedStatement.setBoolean(3, entity.getIsTestDone());
            preparedStatement.setInt(4, entity.getResultPoints());
            preparedStatement.setInt(5, entity.getResultID());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return entity;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTRESULT_DELETE_BY_ID.query())) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return true;
    }

    public ArrayList<TestResult> getResultsByUserId(Integer userId) {
        ArrayList<TestResult> results = new ArrayList<>();

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTRESULT_RESULTS_BY_ID.query())) {
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TestResult result = new TestResult();
                result.setResultID(resultSet.getInt(1));
                result.setUserID(userId);
                result.setTestID(resultSet.getInt(3));
                result.setIsTestDone(resultSet.getBoolean(4));
                result.setResultPoints(resultSet.getInt(5));
                results.add(result);
            }
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return results;
    }
}
