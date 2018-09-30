package by.epam.university.dao.impl;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.DAOManager;
import by.epam.university.dao.connection.ConnectionPool;
import by.epam.university.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

/**
 * A factory for creating transactional and non transactional MySqlDAO
 * factories.
 */
public class MySqlDAOFactory extends DAOFactory {

    private static final Logger LOGGER = LogManager.getLogger(MySqlDAOFactory.class);

    private static final DAOManager daoManager = new NonTransactionalDAOManager();

    @Override
    public DAOManager getNonTransactionalDAOManager() {
        return daoManager;
    }

/*    @Override
    public DAOManager getTransactionalDAOManager() {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Exception during getting a connection from connection pool", e);
            throw new RuntimeException("Exception during getting a connection from connection pool.", e);
        }
        return new TransactionalDAOManager(connection);
    }
*/
}
