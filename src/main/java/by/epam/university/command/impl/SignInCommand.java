package by.epam.university.command.impl;

import by.epam.university.command.Command;
import by.epam.university.command.constant.PathConstants;
import by.epam.university.content.NavigationType;
import by.epam.university.content.RequestContent;
import by.epam.university.content.RequestResult;
import by.epam.university.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The command for moving to the login and password verification menu.
 */
public class SignInCommand implements Command {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SignInCommand.class);

    /**
     * Path to main page.
     */
    private static final String LOGIN_PAGE
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.LOGIN_PAGE);

    /**
     * {@inheritDoc}
     */
    public RequestResult execute(final RequestContent requestContent) {
        return new RequestResult(NavigationType.REDIRECT, LOGIN_PAGE);
    }
}
