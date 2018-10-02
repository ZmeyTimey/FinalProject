package by.epam.university.controller;

import by.epam.university.dao.connection.ConnectionPool;
import by.epam.university.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * The class initializes and destroys the connection pool
 * when application starts and shuts down respectively.
 *
 */
@WebListener()
public class ContextListener implements ServletContextListener {

    /**
     * {@link Logger} class instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ContextListener.class);

    /**
     * Name of bundle with database properties.
     */
    private static final String DB_PROPERTIES = "db";

    /**
     * {@inheritDoc}
     */
    @Override
    public void contextInitialized(final ServletContextEvent contextEvent) {

        try {
            ConnectionPool.getInstance().initialize(DB_PROPERTIES);
            LOGGER.log(Level.DEBUG, "Connection pool was initialized");

        } catch (ConnectionPoolException e) {
            LOGGER.log(Level.ERROR, "Connection pool wasn't initialized");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void contextDestroyed(final ServletContextEvent contextEvent) {

        try {
            ConnectionPool.getInstance().destroy();
            LOGGER.log(Level.DEBUG, "Connection pool was closed");
            ConnectionPool.getInstance().deregisterAllDrivers();
            LOGGER.log(Level.DEBUG, "Drivers were deregistered");

        } catch (ConnectionPoolException e) {
            LOGGER.log(Level.ERROR,
                    "Connection pool wasn't closed correctly");
        }
    }
}
