package by.epam.university.dao;

import by.epam.university.dao.impl.UserDAOImpl;

/**
 * Factory for getting instances of DAO classes.
 */
public final class DAOFactory {

    /**
     * Instance of DAOFactory.
     */
    private static final DAOFactory INSTANCE = new DAOFactory();

    /**
     * {@link DAOFactory} instance.
     */
    private static final UserDAO USER_DAO = new UserDAOImpl();

    /**
     * Prevents getting more than one instance of this class.
     */
    private DAOFactory() {
    }

    /**
     * Gests the instance of DAO Factory.
     * @return instance.
     */
    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Gets UserDAO instance.
     * @return UserDAO.
     */
    public UserDAO getUserDAO() {
        return USER_DAO;
    }
}
