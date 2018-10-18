package by.epam.university.service.impl;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.FacultyDAO;
import by.epam.university.dao.UserDAO;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.User;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.ValidationException;
import by.epam.university.service.util.PasswordEncrypter;
import by.epam.university.service.validator.Validator;

import java.util.List;

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

    /**
     * {@link FacultyDAO} instance.
     */
    private static FacultyDAO facultyDAO = daoFactory.getFacultyDAO();

    /**
     * {@inheritDoc}
     */
    @Override
    public int register(final User user, final String facultyName)
            throws ValidationException, ServiceException {

                if (!Validator.getInstance()
                        .validateRegistrationInputData(user)) {
            throw new ValidationException("User's data are invalid!");
        }

        try {
            if (userDAO.isLoginExists(user.getLogin())) {
                throw new ServiceException("Login already exists");
            }
            if (userDAO.isEmailAlreadyExists(user)) {
                throw new ValidationException("Email already exists");
            }

            String securePass
                    = PasswordEncrypter.getInstance()
                    .encryptPassword(user.getPassword());
            user.setPassword(securePass);

            String facultyId = facultyDAO.getFacultyIdByName(facultyName);
            user.setFacultyId(facultyId);

            return userDAO.addUser(user);

        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


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
            User user = userDAO.getUserByLoginAndPassword(login, password);

            if (user == null) {
                throw new ServiceException("No such user exists");
            }

            return user;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public User fillInPersonalInfo(final int id)
        throws ServiceException {

        try {
            return userDAO.getUserPersonalInfo(id);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public void editPersonalInfo(final User user)
            throws ValidationException, ServiceException {

        if (!Validator.getInstance().validatePersonalUserData(user)) {
            throw new ValidationException("User's data are invalid!");
        }

        try {
            userDAO.setUserPersonalInfo(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUserList() throws ServiceException {

        try {
            return userDAO.getUsersWithAppl();

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
