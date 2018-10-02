package by.epam.university.command.impl;

import by.epam.university.command.Command;
import by.epam.university.command.constant.PathConstants;
import by.epam.university.content.NavigationType;
import by.epam.university.content.RequestContent;
import by.epam.university.content.RequestResult;
import by.epam.university.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * The command for logging out.
 */
public class LogoutCommand implements Command {

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
     * {@inheritDoc}
     */
    public RequestResult execute(final RequestContent requestContent)
            throws IOException {

        requestContent.setSessionToBeInvalidated(true);

        return new RequestResult(NavigationType.REDIRECT,
                CONFIGURATION_MANAGER.getPath(PathConstants.LOGIN_PAGE));

    }
}
