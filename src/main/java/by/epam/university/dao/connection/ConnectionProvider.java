package by.epam.university.dao.connection;

import by.epam.university.dao.exception.ConnectionPoolException;
import by.epam.university.dao.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class that provides both isTransactional
 * and non-isTransactional connections.
 */
public class ConnectionProvider {

    /**
     * Logger instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ConnectionProvider.class);
    /**
     * The instance of Connection Provider.
     */
    private static final ConnectionProvider INSTANCE = new ConnectionProvider();

    /**
     * Used connection.
     */
    private Connection connection;

    private static ThreadLocal<Connection> local = new ThreadLocal<>();

    /**
     * Shows is the selected operation transactional.
     */
    private boolean isTransactional;

    /**
     * Gets an INSTANCE of Connection Provider.
     * @return INSTANCE.
     */
    public static ConnectionProvider getInstance() {
        return INSTANCE;
    }

    /**
     * Returns connection. If connection is transactional then it's taken
     * from {@code ThreadLocal}
     * @return connection
     * @throws ConnectionPoolException the connection pool exception
     *
     */
    public Connection obtainConnection() throws ConnectionPoolException {

        if (!isTransactional) {
            connection = ConnectionPool.getInstance().takeConnection();

            LOGGER.log(Level.DEBUG,
                    "Non-isTransactional connection was taken " + connection);
            return connection;
        }

        LOGGER.log(Level.DEBUG,
                "Transactional connection was taken from threadLocal "
                        + local.get());
        connection = local.get();
        return connection;
    }

    /**
     * Starts transaction. Places connection into {@code ThreadLocal}
     * in order to be shared
     * @throws DAOException the dao exception
     *
     */
    public void startTransaction() throws DAOException {

        try {
            isTransactional = true;
            connection = ConnectionPool.getInstance().takeConnection();

            LOGGER.log(Level.DEBUG, "Transaction was started by " + connection);
            connection.setAutoCommit(false);
            local.set(connection);
            LOGGER.log(Level.DEBUG, "Connection in threadLocal " + local.get());

        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.log(Level.ERROR, "Starting transaction failed");
            throw new DAOException(e);
        }
    }

    /**
     * Commits transaction.
     *
     * @throws DAOException the dao exception
     */
    public void commitTransaction() throws DAOException {
        connection = local.get();

        if (connection != null) {

            try {
                connection.commit();
                LOGGER.log(Level.DEBUG, "Committing is done. Connection "
                        + connection + " closed");

            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Committing transaction failed");
                throw new DAOException(e);
            }
        }
    }


    /**
     * Rollbacks transaction if needed.
     *
     */
    public void abortTransaction() {
        connection = local.get();

        if (connection != null) {

            try {
                connection.rollback();
                LOGGER.log(Level.DEBUG,
                        "Rollback was executed by " + connection);

            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Rollbacking transaction failed");
            }
        }
    }


    /**
     * Ends transaction. Removes connection from {@code ThreadLocal}
     * and sets {@code isTransactional} false
     */
    public void endTransaction() {

        try {
            if (connection != null) {

                connection.setAutoCommit(true);
                connection.close();
                local.remove();
                isTransactional = false;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Closing transaction failed");
        }
    }


    /**
     * Closes the connection if it's non-isTransactional.
     *
     * @param cn the connection
     *
     */
    public void close(final Connection cn) {

        if (!isTransactional) {
            try {
                cn.close();
                LOGGER.log(Level.DEBUG, "Non-trans connection"
                        + connection + " was returned to the pool");
            } catch (SQLException e) {
                LOGGER.log(Level.WARN,
                        "Connection wasn't returned to the pool");
            }
        }
    }

    /**
     * Sets transaction isolation.
     *
     * @param lvl the lvl of isolation
     *
     */
    public void setTransactionIsolation(final int lvl) {

        try {
            local.get().setTransactionIsolation(lvl);
            LOGGER.log(Level.DEBUG, "Connection lvl was set to "
                    + local.get().getTransactionIsolation());
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Setting transaction lvl failed");
        }
    }

    /**
     * Closes resources.
     *
     * @param rs the {@link ResultSet}
     * @param st the {@link Statement}
     *
     */
    public void closeResources(final ResultSet rs, final Statement st) {

        if (rs != null) {
            try {
                rs.close();
                LOGGER.log(Level.DEBUG, "ResultSet was closed");
            } catch (SQLException e) {
                LOGGER.log(Level.WARN, "ResultSet wasn't closed");
            }
        }
        closeResources(st);
    }

    /**
     * Closes resources.
     *
     * @param st {@link Statement}
     *
     */
    public void closeResources(final Statement st) {

        if (st != null) {
            try {
                st.close();
                LOGGER.log(Level.DEBUG, "Statement was closed");
            } catch (SQLException e) {
                LOGGER.log(Level.WARN, "Statement wasn't closed");
            }
        }
    }

    /**
     * Defines whether it's a transaction.
     *
     * @return the boolean
     *
     */
    public boolean isTransactional() {
        return isTransactional;
    }

    /**
     * If is true, then the next connection given will be isTransactional.
     *
     * @param transactional the isTransactional
     *
     */
    public void setTransactional(final boolean transactional) {
        isTransactional = transactional;
    }
}
