package by.epam.university.command.impl;

import by.epam.university.command.Command;
import by.epam.university.command.constant.PathConstants;
import by.epam.university.command.constant.RequestConstants;
import by.epam.university.command.constant.SessionConstants;
import by.epam.university.content.NavigationType;
import by.epam.university.content.RequestContent;
import by.epam.university.content.RequestResult;
import by.epam.university.model.User;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.ValidationException;
import by.epam.university.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Open user's personal data form to fill or change it.
 */
public class UserInfoEditCommand implements Command {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(UserInfoEditCommand.class);

    private static final String SUCCESS_PAGE
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.SUCCESS_PAGE);

    private static final String ERROR_PAGE
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.ERROR_PAGE);

    /**
     * {@inheritDoc}
     */
    @Override
    public RequestResult execute(final RequestContent requestContent) {

        String path;

        try {
            UserService userService
                    = ServiceFactory.getInstance().getUserService();
            User user = createUser(requestContent);
            userService.editPersonalInfo(user);

            path = SUCCESS_PAGE;

        } catch (ValidationException e) {
            LOGGER.log(Level.WARN,
                    "Validation failed while editing personal info");
            path = ERROR_PAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.WARN, e.getMessage());
            path = ERROR_PAGE;
        }
        return new RequestResult(NavigationType.REDIRECT, path);
    }

    /**
     * Creates {@link User} instance and fills it with personal data.
     * @param requestContent
     *            an object of {@link RequestContent}
     *            the wrapper class for {@code request}
     * @return User
     */
    private User createUser(final RequestContent requestContent) {

        User user = new User();

        user.setId((int) requestContent.getSessionAttribute(
                SessionConstants.USER_ID));
        user.setLogin(requestContent.getSessionAttribute(
                SessionConstants.LOGIN).toString());
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
        user.setPassword(requestContent.getParameter(
                RequestConstants.USER_PASSWORD));

        return user;
    }
}
