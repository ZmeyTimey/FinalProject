package by.epam.university.dao.impl;

import by.epam.university.dao.DAOManager;
import by.epam.university.dao.UserDAO;
import by.epam.university.dao.exception.DAOException;

/**
 * It is a factory for creating DAO implementation objects as singletons.
 * Doesn't support transactional operations.
 */
public class NonTransactionalDAOManager implements DAOManager {

    private static final UserDAO userDAO = new UserDAOImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startTransaction() throws DAOException {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTransactionIsolation(int level) throws DAOException {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commit() throws DAOException {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollback() throws DAOException {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws DAOException {
        throw new UnsupportedOperationException();
    }

}
