package by.epam.university.service.impl;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.DAOManager;
import by.epam.university.dao.UserDAO;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.User;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.ValidationException;
import by.epam.university.service.util.HashUtil;

/**
 * It is a class for working with user operations.
 */
public class UserServiceImpl implements UserService {

    private static DAOManager daoManager = DAOFactory.getFactory().getNonTransactionalDAOManager();

    private static UserDAO userDAO = daoManager.getUserDAO();

    @Override
    public Integer registrate(User user) throws ValidationException, ServiceException {
        return null;
    }

    @Override
    public User logIn(String login, String password) throws ValidationException, ServiceException {

        try {
            String hashedPassword = HashUtil.toHash(password);
            return userDAO.getUser(login, hashedPassword);
        } catch (DAOException e) {
            throw new ServiceException("Exception during user signing in.", e);
        }
    }

    @Override
    public boolean isUserFillFullInfo(Integer idUser) throws ServiceException {
        try {
            String name = userDAO.getUserName(idUser);
            return name != null;
        } catch (DAOException e) {
            throw new ServiceException(
                    "Exception during checking filling name"
                            + "by user as criteria of filling full info.", e);
        }
    }

    @Override
        public void setUserFullInfo(User user) throws ValidationException, ServiceException {

        try {
            userDAO.setUserFullInfo(user);
        } catch (DAOException e) {
            throw new ServiceException(
                    "Exception during setting full info about user.", e);
        }
    }
}