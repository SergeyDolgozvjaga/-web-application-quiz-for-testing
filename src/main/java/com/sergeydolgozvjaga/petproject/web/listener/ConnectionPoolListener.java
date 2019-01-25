package com.sergeydolgozvjaga.petproject.web.listener;

import com.sergeydolgozvjaga.petproject.connection.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Listener close connection pool
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */

@WebListener
public class ConnectionPoolListener implements ServletContextListener {

    private static Logger LOG = LoggerFactory.getLogger(ConnectionPoolListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {}

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.info("Closing connection pool");
        ConnectionPool.getInstance().closeConnectionPool();
    }
}