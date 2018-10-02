package by.epam.university.dao.connection;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

/**
 * Defines pooled connection that is Proxy for simple {@link Connection}.
 */
public class PooledConnection implements Connection {
    /**
     * Connection instance.
     */
    private Connection connection;

    /**
     * Wraps the {@link Connection}.
     * @param con
     *            the connection
     * @throws SQLException
     *             if a database access error occurs
     */
    public PooledConnection(final Connection con) throws SQLException {
        connection = con;
        this.connection.setAutoCommit(true);
    }

    /**
     * Not really closes the connection.
     * Only returns the connection to the connection pool.
     * Before returning sets its settings to default.
     * @throws SQLException
     *             if a database access error occurs
     */
    @Override
    public void close() throws SQLException {
        if (connection.isClosed()) {
            throw new SQLException("Attempt to close closed connection");
        }

        if (connection.isReadOnly()) {
            connection.setReadOnly(false);
        }

        BlockingQueue<Connection> takenConnections
                = ConnectionPool.getInstance().getTakenConnections();
        if (!takenConnections.remove(this)) {
            throw new SQLException(
                    "Failed to delete connection from given away pool");
        }

        BlockingQueue<Connection> freeConnections
                = ConnectionPool.getInstance().getFreeConnections();
        if (!freeConnections.offer(this)) {
            throw new SQLException("Failed to allocate connection to pool");
        }
    }

    /**
     * Really close the connection (return the connection to the data base).
     *
     * @throws SQLException
     *             if a database access error occurs
     */
    public void reallyClose() throws SQLException {
        connection.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return connection.isWrapperFor(iface);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        return connection.unwrap(iface);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void abort(final Executor executor) throws SQLException {
        connection.abort(executor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Array createArrayOf(final String typeName,
                               final Object[] elements)
            throws SQLException {
        return connection.createArrayOf(typeName, elements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Blob createBlob() throws SQLException {
        return connection.createBlob();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Clob createClob() throws SQLException {
        return connection.createClob();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public NClob createNClob() throws SQLException {
        return connection.createNClob();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLXML createSQLXML() throws SQLException {
        return connection.createSQLXML();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Statement createStatement(final int resultSetType,
                                     final int resultSetConcurrency)
            throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Statement createStatement(final int resultSetType,
                                     final int resultSetConcurrency,
                                     final int resultSetHoldability)
            throws SQLException {
        return connection.createStatement(
                resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Struct createStruct(final String typeName,
                               final Object[] attributes)
            throws SQLException {
        return connection.createStruct(typeName, attributes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClientInfo(final String name) throws SQLException {
        return connection.getClientInfo(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSchema() throws SQLException {
        return connection.getSchema();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final int timeout) throws SQLException {
        return connection.isValid(timeout);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String nativeSQL(final String sql) throws SQLException {
        return connection.nativeSQL(sql);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CallableStatement prepareCall(final String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CallableStatement prepareCall(final String sql,
                                         final int resultSetType,
                                         final int resultSetConcurrency)
            throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CallableStatement prepareCall(final String sql,
                                         final int resultSetType,
                                         final int resultSetConcurrency,
                                         final int resultSetHoldability)
            throws SQLException {
        return connection.prepareCall(
                sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PreparedStatement prepareStatement(final String sql)
            throws SQLException {
        return connection.prepareStatement(sql);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
                                              final int autoGeneratedKeys)
            throws SQLException {
        return connection.prepareStatement(sql, autoGeneratedKeys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
                                              final int[] columnIndexes)
            throws SQLException {
        return connection.prepareStatement(sql, columnIndexes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
                                              final String[] columnNames)
            throws SQLException {
        return connection.prepareStatement(sql, columnNames);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
                                              final int resultSetType,
                                              final int resultSetConcurrency)
            throws SQLException {
        return connection.prepareStatement(
                sql, resultSetType, resultSetConcurrency);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
                                              final int resultSetType,
                                              final int resultSetConcurrency,
                                              final int resultSetHoldability)
            throws SQLException {
        return connection.prepareStatement(
                sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void releaseSavepoint(final Savepoint savepoint)
            throws SQLException {
        connection.releaseSavepoint(savepoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollback(final Savepoint savepoint) throws SQLException {
        connection.rollback(savepoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAutoCommit(final boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCatalog(final String catalog) throws SQLException {
        connection.setCatalog(catalog);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClientInfo(final Properties properties)
            throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClientInfo(final String name,
                              final String value)
            throws SQLClientInfoException {
        connection.setClientInfo(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHoldability(final int holdability) throws SQLException {
        connection.setHoldability(holdability);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNetworkTimeout(final Executor executor,
                                  final int milliseconds)
            throws SQLException {
        connection.setNetworkTimeout(executor, milliseconds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setReadOnly(final boolean readOnly) throws SQLException {
        connection.setReadOnly(readOnly);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Savepoint setSavepoint(final String name) throws SQLException {
        return connection.setSavepoint(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSchema(final String schema) throws SQLException {
        connection.setSchema(schema);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTransactionIsolation(final int level) throws SQLException {
        connection.setTransactionIsolation(level);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTypeMap(final Map<String, Class<?>> map)
            throws SQLException {
        connection.setTypeMap(map);
    }

}
