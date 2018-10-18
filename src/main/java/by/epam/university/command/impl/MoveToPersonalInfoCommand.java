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
import by.epam.university.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The command for moving to the user's personal data edition menu.
 */
public class MoveToPersonalInfoCommand implements Command {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SignUpCommand.class);

    /**
     * Path to the target page.
     */
    private static final String EDIT_PERSONAL_DATA
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.EDIT_PERSONAL_DATA);

    /**
     * Path to fail page.
     */
    private static final String FAIL_PAGE
            = ConfigurationManager.getInstance().getPath(
            PathConstants.MAIN_PAGE);
    /**
     * {@link UserService} instance.
     */
    private static final UserService USER_SERVICE
            = ServiceFactory.getInstance().getUserService();

    /**
     * {@inheritDoc}
     */
    public RequestResult execute(final RequestContent requestContent) {

        LOGGER.log(Level.DEBUG,
                "Moving to the user's personal info edition menu");

        int id = (int) requestContent.getSessionAttribute(
                SessionConstants.USER_ID);

        try {
            User user = USER_SERVICE.fillInPersonalInfo(id);
            requestContent.setAttribute(RequestConstants.USER, user);

            return new RequestResult(
                    NavigationType.FORWARD, EDIT_PERSONAL_DATA);

        } catch (ServiceException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.log(Level.WARN, e.getMessage());
            }
            e.printStackTrace();
        }
        return new RequestResult(NavigationType.FORWARD, FAIL_PAGE);
    }
}
