package by.epam.university.dao;

import by.epam.university.dao.connection.ConnectionPool;
import by.epam.university.dao.connection.ConnectionProvider;
import by.epam.university.dao.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The Class AbstractDAO is used as superclass for another
 * AbstractDAO classes working
 * with a data base.
 */
public class AbstractDAO {

    /**
     * The Connection provider.
     */
    protected final ConnectionProvider connectionProvider
            = ConnectionProvider.getInstance();

//    private boolean transactional;

    /**
     * Instantiates a new abstract AbstractDAO for non transactional operations.
     */
    protected AbstractDAO() {
    }

//    /**
//     * Instantiates a new abstract AbstractDAO for transactional operations.
//     *
//     * @param connection
//     *            the connection that can be transferred between
//     *            different AbstractDAO
//     */
//    public AbstractDAO(Connection connection) {
//        this.connection = connection;
//        this.transactional = true;
//    }
//
//    public Connection getConnection() throws ConnectionPoolException {
//        if (connection == null) {
//            return ConnectionPool.getInstance().takeConnection();
//        } else {
//            return connection;
//        }
//    }
//
//    public void closeNonTransactionalConnection(Connection connection) throws SQLException {
//        if ( ! transactional) {
//            connection.close();
//        }
//    }
}
