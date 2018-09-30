package by.epam.university.dao;

import by.epam.university.dao.connection.ConnectionPool;
import by.epam.university.dao.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The Class AbstractDAO is used as superclass for another AbstractDAO classes working
 * with a data base.
 */
public class AbstractDAO {
    private Connection connection;

    private boolean transactional;

    /**
     * Instantiates a new abstract AbstractDAO for non transactional operations.
     */
    public AbstractDAO() {
    }

    /**
     * Instantiates a new abstract AbstractDAO for transactional operations.
     *
     * @param connection
     *            the connection that can be transferred between different AbstractDAO
     */
    public AbstractDAO(Connection connection) {
        this.connection = connection;
        this.transactional = true;
    }

    public Connection getConnection() throws ConnectionPoolException {
        if (connection == null) {
            return ConnectionPool.getInstance().getConnection();
        } else {
            return connection;
        }
    }

    public void closeNonTransactionalConnection(Connection connection) throws SQLException {
        if ( ! transactional) {
            connection.close();
        }
    }
}
