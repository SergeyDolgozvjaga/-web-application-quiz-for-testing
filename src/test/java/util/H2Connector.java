package util;

import com.sergeydolgozvjaga.petproject.connection.ConnectionPool;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public  class H2Connector {

    static final Logger LOG = LoggerFactory.getLogger(H2Connector.class);
    private Database database;
    private Liquibase liquibase;
    private Connection connection;

    public H2Connector() {
        ConnectionPool.setPropertyPath("h2.properties");
        connection = ConnectionPool.getInstance().getConnection();
    }

    public synchronized void buildDatabase() throws ClassNotFoundException, LiquibaseException {

        database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        liquibase = new Liquibase("liquibase/db-changelog-master.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update("public");
    }

    public void dropDatabase() throws DatabaseException, LockException {

        liquibase.dropAll();

        try {
            connection.close();
        } catch (SQLException sqlEx){
            LOG.error(sqlEx.getMessage());
        }
    }
}

