package by.epam.university.dao;

import by.epam.university.dao.impl.MySqlDAOFactory;

/**
 * An abstract factory for creating transactional and not transactional DAO
 * factories.
 */
public abstract class DAOFactory {

    /**
     * Gets mysql DAO factory.
     *
     * @return new mysql DAO factory.
     */
    public static DAOFactory getFactory() {
        return new MySqlDAOFactory();
    }

    // public abstract DAOManager getTransactionalDAOManager();

    public abstract DAOManager getNonTransactionalDAOManager();

}
