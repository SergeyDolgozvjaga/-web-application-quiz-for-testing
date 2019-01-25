package com.sergeydolgozvjaga.petproject.dao;

import com.sergeydolgozvjaga.petproject.connection.ConnectionToDatabase;

import java.sql.SQLException;
import java.util.List;

/**
 * Abstract class for DAO pattern
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public abstract class AbstractDao<T, U> extends ConnectionToDatabase {

    protected AbstractDao() {}

    public abstract List<T> getAll() throws SQLException;

    public abstract T update(T entity) throws SQLException;

    public abstract T getEntityById(U id) throws SQLException;

    public abstract boolean delete(U id) throws SQLException;

    public abstract boolean create(T entity) throws SQLException;
}
