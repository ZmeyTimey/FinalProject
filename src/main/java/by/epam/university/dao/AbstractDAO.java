package by.epam.university.dao;

import by.epam.university.dao.connection.ConnectionProvider;

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
}
