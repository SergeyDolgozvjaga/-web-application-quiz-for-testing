package com.sergeydolgozvjaga.petproject.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * Make connection to database
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class ConnectionToDatabase {

    private static Logger LOG = LoggerFactory.getLogger(Connection.class);

    Connection connection;
    ConnectionPool connectionPool;

    private PreparedStatement preparedStatement;

    protected ConnectionToDatabase() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException sqlEx) {
            LOG.info(sqlEx.getMessage());
            LOG.warn(sqlEx.getMessage());
        }
    }

    protected PreparedStatement openPrepareStatement(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException sqlEx) {
            LOG.info(sqlEx.getMessage());
            LOG.warn(sqlEx.getMessage());
        }
        return preparedStatement;
    }

    protected void closePrepareStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException sqlEx) {
                LOG.info(sqlEx.getMessage());
                LOG.warn(sqlEx.getMessage());
            }
        }
    }
}
