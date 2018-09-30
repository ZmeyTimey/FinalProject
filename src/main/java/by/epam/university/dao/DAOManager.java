package by.epam.university.dao;

import by.epam.university.dao.exception.DAOException;

/**
 * Defines the methods for DAO factories
 */
public interface DAOManager {

    UserDAO getUserDAO();

    void startTransaction() throws DAOException;

    void setTransactionIsolation(int level) throws DAOException;

    void commit() throws DAOException;

    void rollback() throws DAOException;

    void close() throws DAOException;
}