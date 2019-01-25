package com.sergeydolgozvjaga.petproject.dao;

import com.sergeydolgozvjaga.petproject.model.TestSector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Realization DAO pattern for TestSector
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class TestSectorDao extends AbstractDao<TestSector, Integer> {

    static final Logger LOG = LoggerFactory.getLogger(TestSectorDao.class);
    private ResultSet resultSet;


    public TestSectorDao() {}


    @Override
    public List<TestSector> getAll(){
        ArrayList<TestSector> userTests = new ArrayList<>();

        try(PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTSECTOR_GET_ALL.query())) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TestSector testSector = new TestSector();
                testSector.setTestID(resultSet.getInt(1));
                testSector.setSubject(resultSet.getString(2));
                testSector.setName(resultSet.getString(3));
                testSector.setDifficultLevel(resultSet.getInt(4));
                testSector.setSolveTime(resultSet.getInt(5));
                userTests.add(testSector);
            }
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return userTests;
    }

    @Override
    public TestSector getEntityById(Integer id) {
        TestSector testSector = new TestSector();

        try(PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTSECTOR_GET_BY_ID.query())) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                testSector.setTestID(id);
                testSector.setSubject(resultSet.getString(2));
                testSector.setName(resultSet.getString(3));
                testSector.setDifficultLevel(resultSet.getInt(4));
                testSector.setSolveTime(resultSet.getInt(5));
            }
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return testSector;
    }

    @Override
    public boolean create(TestSector entity) {
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTSECTOR_CREATE.query())){
            preparedStatement.setString(1, entity.getSubject());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3, entity.getDifficultLevel());
            preparedStatement.setInt(4, entity.getSolveTime());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return true;
    }

    @Override
    public TestSector update(TestSector entity){
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTSECTOR_UPDATE.query())){
            preparedStatement.setString(1, entity.getSubject());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3, entity.getDifficultLevel());
            preparedStatement.setInt(4, entity.getSolveTime());
            preparedStatement.setInt(5, entity.getTestID());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return entity;
    }

    @Override
    public boolean delete(Integer id) {
        try(PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.TESTSECTOR_DELETE_BY_ID.query())) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return true;
    }
}
