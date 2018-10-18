package by.epam.university.command.impl;

import by.epam.university.command.Command;
import by.epam.university.command.constant.PathConstants;
import by.epam.university.command.constant.RequestConstants;
import by.epam.university.command.constant.SessionConstants;
import by.epam.university.content.NavigationType;
import by.epam.university.content.RequestContent;
import by.epam.university.content.RequestResult;
import by.epam.university.model.Faculty;
import by.epam.university.model.Role;
import by.epam.university.model.User;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.ValidationException;
import by.epam.university.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * The command for entrant registration.
 */
public class RegistrationCommand implements Command {
    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RegistrationCommand.class);

    /**
     * Path to entrant menu page.
     */
    private static final String SUCCESS_PAGE
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.ENTRANT);

    /**
     * Path to registration page.
     */
    private static final String REGISTRATION
            = ConfigurationManager.getInstance().getPath(
                    PathConstants.REGISTRATION);

    /**
     * Path to fail page.
     */
    private static final String REGISTRATION_FAIL_PAGE
            = ConfigurationManager.getInstance().getPath(
                    PathConstants.REGISTRATION);

    /**
     * Registered user's role.
     */
    private static final Role USER_ROLE = Role.ENTRANT;

    /**
     * {@inheritDoc}
     */
    public RequestResult execute(final RequestContent requestContent)
            throws ServletException, IOException {

        UserService userService = ServiceFactory.getInstance().getUserService();

        User user = createUser(requestContent);
        String facultyName = requestContent.getParameter(
                RequestConstants.FACULTY_NAME);

        try {
            int userId = userService.register(user, facultyName);
            String facultyId = user.getFacultyId();

            requestContent.setSessionAttribute(
                    SessionConstants.USER_ID, userId);
            requestContent.setSessionAttribute(
                    SessionConstants.LOGIN, user.getLogin());
            requestContent.setSessionAttribute(
                    SessionConstants.ROLE, USER_ROLE);
            requestContent.setSessionAttribute(
                    SessionConstants.FACULTY_ID, facultyId);

            return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);

        } catch (ValidationException | ServiceException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.log(Level.WARN, e.getMessage());
            }
        }
        return new RequestResult(NavigationType.REDIRECT,
                REGISTRATION_FAIL_PAGE);
    }

    /**
     * Creates {@link User} instance.
     * @param requestContent is {@link RequestContent} instance.
     * @return user with the parameters taken from request.
     */
    private User createUser(final RequestContent requestContent) {
        User user = new User();

        user.setLogin(requestContent.getParameter(
                RequestConstants.USER_LOGIN));
        user.setPassword(requestContent.getParameter(
                RequestConstants.USER_PASSWORD));
        user.setName(requestContent.getParameter(
                RequestConstants.USER_NAME));
        user.setMiddlename(requestContent.getParameter(
                RequestConstants.USER_MIDDLENAME));
        user.setSurname(requestContent.getParameter(
                RequestConstants.USER_SURNAME));
        user.setEmail(requestContent.getParameter(
                RequestConstants.USER_EMAIL));
        user.setPhone(requestContent.getParameter(
                RequestConstants.USER_PHONE));

        Role role = Role.ENTRANT;
        user.setRole(role);

        return user;
    }
}
