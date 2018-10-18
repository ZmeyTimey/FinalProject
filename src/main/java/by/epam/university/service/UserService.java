package by.epam.university.service;

import by.epam.university.model.Speciality;
import by.epam.university.model.User;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.ValidationException;

import java.util.List;

/**
 * Works with user operations.
 */
public interface UserService {

    /**
     * Register specified user.
     *
     * @param user
     *            the user
     * @param facultyName
     *            the name of faculty where user registers
     * @return the id of registered user
     * @throws ValidationException
     *             the exception during validation of user parameters
     * @throws ServiceException
     *             the service layer exception
     */
    int register(User user, String facultyName)
            throws ValidationException, ServiceException;

    /**
     * Returns the {@link User} who appropriates specified
     * login password. Returns null if such user is not found.
     *
     * @param login
     *            the login
     * @param password
     *            the password
     * @return the user who appropriates specified login and
     *         password. Returns null if such user is not found.
     * @throws ValidationException
     *             the exception during login and password validation
     * @throws ServiceException
     *             the service layer exception
     */
    User logIn(String login, String password)
            throws ValidationException, ServiceException;

    /**
     * Creates {@link User} instance filled with user's personal information.
     * @param id user's id
     * @return {@link User} instance
     * @throws ServiceException
     *             the service layer exception
     */
    User fillInPersonalInfo(int id) throws ServiceException;

    /**
     * Sets the user full info.
     *
     * @param user
     *            the user object containing full info
     *            about user
     * @throws ValidationException
     *             the exception during validation of user parameters
     * @throws ServiceException
     *             the service layer exception
     */
    void editPersonalInfo(User user)
            throws ValidationException, ServiceException;

    /**
     * Gets a list of users who sent applications for verification.
     * @return list of users
     * @throws ServiceException
     *             the service layer exception
     */
    List<User> getUserList() throws ServiceException;
}
