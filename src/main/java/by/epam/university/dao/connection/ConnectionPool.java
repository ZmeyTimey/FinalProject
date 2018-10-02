package by.epam.university.dao.connection;

import by.epam.university.dao.exception.ConnectionPoolException;
import by.epam.university.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Driver;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The class for instantiation of connection pool.
 */
public final class ConnectionPool {

    /**
     * Logger instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ConnectionPool.class);

    /** The queue of free connections. */
    private BlockingQueue<Connection> freeConnections;
    /** The queue of taken connections. */
    private BlockingQueue<Connection> takenConnections;

    /** Name of driver used to connect to the database. */
    private String driverName;
    /** URL of data base. */
    private String dbUrl;
    /** User's name for connecting to data base. */
    private String user;
    /** Password for connecting to data base. */
    private String password;
    /** Size of connection pool. */
    private int poolSize;

    /**
     * Single instance of ConnectionPool.
     */
    private static ConnectionPool instance = new ConnectionPool();

    /**
     * Shows is Connection Pool initialized.
     */
    private static final AtomicBoolean IS_INITIALIZED
            = new AtomicBoolean(false);

    /**
     * Lock for preventing entering getInstance()
     * method by more than one thread.
     */
    private static final Lock LOCK = new ReentrantLock();

    /**
     * Prevents getting more than one instance of this class.
     */
    private ConnectionPool() {
    }

    /**
     * Gets instance of ConnectionPool.
     * @return instance.
     */
    public static ConnectionPool getInstance() {

        if (!IS_INITIALIZED.get()) {
            LOCK.lock();

            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    IS_INITIALIZED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * Gets free connections.
     * @return free connections.
     */
    public BlockingQueue<Connection> getFreeConnections() {
        return freeConnections;
    }

    /**
     * Gets taken connections.
     * @return taken connections.
     */
    public BlockingQueue<Connection> getTakenConnections() {
        return takenConnections;
    }

    /**
     * Initializes the Connection Pool. Gets data base and connection pool
     * configurations with a help of {@link ConfigurationManager}.
     * Creates a new queue of connections of defined size and fills it
     * with pooled connections to the defined data base
     * (see {@link PooledConnection}
     *
     * @throws ConnectionPoolException
     *             exception during connection pool initialization
     */
    public void initialize(final String dbProperties)
            throws ConnectionPoolException {

        initPoolData(dbProperties);

        try {
            Class.forName(driverName);

            freeConnections = new ArrayBlockingQueue<>(poolSize);
            takenConnections = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection
                        = DriverManager.getConnection(dbUrl, user, password);
                freeConnections.add(new PooledConnection(connection));
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionPoolException(
                    "Exception during connection pool initialization.", e);
        }
    }

    /**
     * Initializes Connection Pool data.
     */
    private void initPoolData(final String dbProperties) {

//        ConfigurationManager configurationManager
//                = ConfigurationManager.getInstance();

        ResourceBundle resource = ResourceBundle.getBundle(dbProperties);

        driverName = resource.getString(DBParameters.DB_DRIVER);

        String unicode = resource.getString((DBParameters.DB_UNICODE));
        String ssl = resource.getString(DBParameters.DB_SSL);
        String timezone = resource.getString(DBParameters.DB_TIMEZONE);
        String url = resource.getString(DBParameters.DB_URL);

        dbUrl = url + unicode + ssl + timezone;
        user = resource.getString(DBParameters.DB_USER);
        password = resource.getString(DBParameters.DB_PASSWORD);
        poolSize = Integer.parseInt(resource.getString(DBParameters.DB_POOL_SIZE));
//        driverName = configurationManager
//                .getDatabaseParameters(dbProperties, DBParameters.DB_DRIVER);
//
//        String url = configurationManager
//                .getDatabaseParameters(dbProperties, DBParameters.DB_URL);
//        String unicode = configurationManager
//                .getDatabaseParameters(dbProperties, DBParameters.DB_UNICODE);
//        String ssl = configurationManager
//                .getDatabaseParameters(dbProperties, DBParameters.DB_SSL);
//        String timezone = configurationManager
//                .getDatabaseParameters(dbProperties, DBParameters.DB_TIMEZONE);
////
//        dbUrl = url + unicode + ssl + timezone;
//
//        user = configurationManager
//                .getDatabaseParameters(dbProperties, DBParameters.DB_USER);
//        password = configurationManager
//                .getDatabaseParameters(dbProperties, DBParameters.DB_PASSWORD);
//
//        poolSize = Integer.parseInt(configurationManager
//                .getDatabaseParameters(dbProperties,
//                        DBParameters.DB_POOL_SIZE));

        if (poolSize < 0) {
            throw new RuntimeException("Pool size incorrectly specified"
                    + " in property file, the number of connections"
                    + " should be positive digit.");
        }
    }

    /**
     * Returns the connection which is {@link PooledConnection}
     * from the connection pool.
     * @return the connection which is {@link PooledConnection}
     * @throws ConnectionPoolException
     *             when it's not possible to take connection
     *             from the connection pool.
     */
    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection;

        try {
            connection = freeConnections.take();
            takenConnections.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(
                    "Exception during getting connection"
                            + "from connection pool.", e);
        }
        return connection;
    }

    /**
     * Deregisters all the drivers.
     * @throws ConnectionPoolException
     *             when it's not possible to deregister driver.
     */
    public void deregisterAllDrivers() throws ConnectionPoolException {

        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();

            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(
                    "Exception while deregistering drivers", e);
        }
    }

    /**
     * Destroys the connection pool.
     *
     * @throws ConnectionPoolException
     *             if exception during closing the connections occurred
     */
    public void destroy() throws ConnectionPoolException {

        for (int i = 0; i < poolSize; i++) {

            try {
                Connection connection = freeConnections.take();
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                ((PooledConnection) connection).reallyClose();

            } catch (SQLException | InterruptedException e) {
                throw new ConnectionPoolException(
                        "Exception during closing the connection pool.", e);
            }
        }
    }

}
