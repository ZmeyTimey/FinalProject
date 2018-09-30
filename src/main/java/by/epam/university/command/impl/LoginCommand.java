package by.epam.university.command.impl;

import by.epam.university.command.Command;
import by.epam.university.command.constant.MessageConstants;
import by.epam.university.command.constant.RequestConstants;
import by.epam.university.command.constant.SessionConstants;
import by.epam.university.command.constant.ViewPath;
import by.epam.university.content.NavigationType;
import by.epam.university.content.RequestContent;
import by.epam.university.content.RequestResult;
import by.epam.university.model.Role;
import by.epam.university.model.User;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.ValidationException;
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
     * {@inheritDoc}
     */
       @Override
        public RequestResult execute(final RequestContent requestContent)
               throws IOException {

           UserService userService
                   = ServiceFactory.getInstance().getUserService();

           try {
               String login
                       = requestContent.getParameter(
                               RequestConstants.USER_LOGIN);
               String password
                       = requestContent.getParameter(
                               RequestConstants.USER_PASSWORD);

               User user = userService.logIn(login, password);

               if (user != null) {

                   Role role = user.getRole();

                   requestContent.setSessionAttribute(
                           SessionConstants.USER_ID, user.getId());
                   requestContent.setSessionAttribute(
                           SessionConstants.LOGIN, user.getLogin());
                   requestContent.setSessionAttribute(
                           SessionConstants.ROLE, role);

                   String viewPath
                           = defineViewPath(role, requestContent).toString();

                   System.out.println(viewPath);

                   return new RequestResult(NavigationType.REDIRECT, viewPath);

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

        private StringBuilder defineViewPath(
                final Role role,
                final RequestContent requestContent) {

            StringBuilder viewPath = new StringBuilder();
            switch (role) {
                case ADMIN:
                    return viewPath
                            .append(requestContent.getCurrentPage())
                            .append(ViewPath.ADMIN_MENU);
                case ENTRANT:
                    return viewPath
                            .append(requestContent.getCurrentPage())
                            .append(ViewPath.ENTRANT_MENU);
                default:
                    return viewPath.append(ViewPath.MAIN_PAGE);
            }
        }
    }
