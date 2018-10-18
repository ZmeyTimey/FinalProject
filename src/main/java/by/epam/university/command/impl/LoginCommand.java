package by.epam.university.command.impl;

import by.epam.university.command.Command;
import by.epam.university.command.constant.PathConstants;
import by.epam.university.command.constant.MessageConstants;
import by.epam.university.command.constant.RequestConstants;
import by.epam.university.command.constant.SessionConstants;
import by.epam.university.content.NavigationType;
import by.epam.university.content.RequestContent;
import by.epam.university.content.RequestResult;
import by.epam.university.model.Role;
import by.epam.university.model.User;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.ValidationException;
import by.epam.university.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Login in command.
 */
public class LoginCommand implements Command {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(LoginCommand.class);

    /**
     * The instance of Configuration Manager.
     */
    private static final ConfigurationManager CONFIGURATION_MANAGER
            = ConfigurationManager.getInstance();

    /**
     * {@link UserService} instance.
     */
    private static final UserService USER_SERVICE
            = ServiceFactory.getInstance().getUserService();

    /**
     * {@inheritDoc}
     */
       @Override
        public RequestResult execute(final RequestContent requestContent)
               throws IOException {

           try {
               String login
                       = requestContent.getParameter(
                               RequestConstants.USER_LOGIN);
               String password
                       = requestContent.getParameter(
                               RequestConstants.USER_PASSWORD);

               User user = USER_SERVICE.logIn(login, password);

               if (user != null) {

                   Role role = user.getRole();
                   String facultyId = user.getFacultyId();

                   requestContent.setSessionAttribute(
                           SessionConstants.USER_ID, user.getId());
                   requestContent.setSessionAttribute(
                           SessionConstants.LOGIN, user.getLogin());
                   requestContent.setSessionAttribute(
                           SessionConstants.ROLE, role);
                   requestContent.setSessionAttribute(
                           SessionConstants.FACULTY_ID, facultyId);
                   requestContent.setSessionAttribute(
                           SessionConstants.IS_APPLICATION_SENT,
                           user.isApplicationSent());
                   requestContent.setSessionAttribute(
                           SessionConstants.IS_USER_ENLISTED,
                           user.isEnlisted());

                   return new RequestResult(NavigationType.REDIRECT,
                           defineViewPath(role));

               } else {
                   requestContent.setSessionAttribute(
                           SessionConstants.LOG_IN_FAIL,
                           MessageConstants.WRONG_LOGIN_OR_PASSWORD);

                   return new RequestResult(
                           NavigationType.REDIRECT,
                           requestContent.getCurrentPage());
                }

           } catch (ValidationException e) {

                LOGGER.warn("Sending invalid data for loginning.", e);
               requestContent.setSessionAttribute(
                       SessionConstants.LOG_IN_FAIL,
                       MessageConstants.INVALID_INPUT);

               return new RequestResult(
                       NavigationType.REDIRECT,
                       requestContent.getCurrentPage());

           } catch (ServiceException e) {
                LOGGER.error("Exception during user loginning.", e);
               return new RequestResult(
                       NavigationType.REDIRECT,
                       requestContent.getCurrentPage());
           }
        }

    /**
     * Defines the page path for admin or entrant.
     * @param role user's role.
     * @return page path.
     */
        private String defineViewPath(
                final Role role) {

            switch (role) {
                case ADMIN:
                    return CONFIGURATION_MANAGER
                            .getPath(PathConstants.ADMIN);
                case ENTRANT:
                    return CONFIGURATION_MANAGER
                            .getPath(PathConstants.ENTRANT);
                default:
                    return CONFIGURATION_MANAGER
                            .getPath(PathConstants.MAIN_PAGE);
            }
       }
}
