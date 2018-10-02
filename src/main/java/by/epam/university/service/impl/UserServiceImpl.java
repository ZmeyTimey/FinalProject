package by.epam.university.service.impl;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.UserDAO;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.User;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.ValidationException;
import by.epam.university.service.validator.Validator;

/**
 * It is a class for working with user operations.
 */
public class UserServiceImpl implements UserService {

    /**
     * {@link DAOFactory} instance for getting objects of DAO classes.
     */
    private static DAOFactory daoFactory = DAOFactory.getInstance();

    /**
     * {@link UserDAO} instance.
     */
    private static UserDAO userDAO = daoFactory.getUserDAO();

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public Integer register(final User user)
//            throws ValidationException, ServiceException {
//        return null;
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User logIn(final String login,
                      final String password)
            throws ValidationException, ServiceException {

        if (!Validator.getInstance().validateLoginInputData(login, password)) {
            throw new ValidationException("User's data are invalid!");
        }

        try {
            if (!userDAO.isLoginExists(login)) {
                throw new ServiceException("No such user exists");
            }

            return userDAO.getUser(login, password);

        } catch (DAOException e) {
            throw new ServiceException("Exception while logging in", e);
        }
    }

//    @Override
//    public boolean isUserFillFullInfo(Integer idUser) throws ServiceException {
//        try {
//            String name = userDAO.getUserName(idUser);
//            return name != null;
//        } catch (DAOException e) {
//            throw new ServiceException(
//                    "Exception during checking filling name"
//                            + "by user as criteria of filling full info.", e);
//        }
//    }

//    @Override
//        public void setUserFullInfo(User user) throws ValidationException, ServiceException {
//
//        try {
//            userDAO.setUserFullInfo(user);
//        } catch (DAOException e) {
//            throw new ServiceException(
//                    "Exception during setting full info about user.", e);
//        }
//    }
}
