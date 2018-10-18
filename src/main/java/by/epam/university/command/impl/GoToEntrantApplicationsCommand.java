package by.epam.university.command.impl;

import by.epam.university.command.Command;
import by.epam.university.command.constant.PathConstants;
import by.epam.university.command.constant.RequestConstants;
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

import java.util.List;

/**
 * The command for moving to the application management page.
 */
public class GoToEntrantApplicationsCommand implements Command {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(GoToSignUpCommand.class);

    /**
     * {@link UserService} instance.
     */
    private static final UserService USER_SERVICE
            = ServiceFactory.getInstance().getUserService();

    /**
     * Path to the target page.
     */
    private static final String SUCCESS_PAGE
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.MANAGE_APPLICATIONS);

    /**
     * Path to fail page.
     */
    private static final String FAIL_PAGE
            = ConfigurationManager.getInstance().getPath(
            PathConstants.MAIN_PAGE);

    /**
     * {@inheritDoc}
     */
    public RequestResult execute(final RequestContent requestContent) {

        LOGGER.log(Level.DEBUG,
                "Moving to the application management page");

        try {
            List<User> userList = USER_SERVICE.getUserList();
            requestContent.setAttribute(RequestConstants.USER_LIST, userList);

            return new RequestResult(
                    NavigationType.FORWARD, SUCCESS_PAGE);

        } catch (ServiceException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }

        return new RequestResult(NavigationType.FORWARD, FAIL_PAGE);
    }
}
