package com.sergeydolgozvjaga.petproject.connection;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Makes a connection pool
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class ConnectionPool {

    private static Logger LOG = LoggerFactory.getLogger(ConnectionPool.class);

    private static volatile ConnectionPool instance;
    private static volatile String liquibasePath = "liquibase/db-liquibase.properties";
    private BoneCP connectionPool;

    private ConnectionPool() throws SQLException {
        try {
            connectionPool = new BoneCP(getConfig());
        } catch (IOException | ClassNotFoundException e) {
            LOG.error(e.getMessage());
        }
    }

    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    try {
                        instance = localInstance = new ConnectionPool();
                    } catch (SQLException e) {
                        LOG.error(e.getMessage());
                    }
                }
            }
        }
        return localInstance;
    }

    public Connection getConnection() {
        try {
            return connectionPool.getConnection();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        throw new RuntimeException("Can't create a connection");
    }

    public static void setPropertyPath(String newDatabasePath) {
        liquibasePath = newDatabasePath;
    }

    public void closeConnectionPool() {
        connectionPool.shutdown();
    }

    private BoneCPConfig getConfig() throws IOException, ClassNotFoundException {
        Properties props = new Properties();
        try (InputStream in = ConnectionPool.class.getClassLoader().getResourceAsStream(liquibasePath)) {
            props.load(in);
        }

        BoneCPConfig config = new BoneCPConfig();
        String drivers = props.getProperty("driver");
        Class.forName(drivers);
        config.setJdbcUrl(props.getProperty("url"));
        config.setUsername(props.getProperty("username"));
        config.setPassword(props.getProperty("password"));
        config.setMinConnectionsPerPartition(1);
        config.setMaxConnectionsPerPartition(15);
        config.setPartitionCount(10);
        return config;
    }
}
