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
 * The command for moving to the registration menu.
 */
public class SignUpCommand implements Command {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SignUpCommand.class);

    /**
     * Path to registration page.
     */
    private static final String REGISTRATION
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.REGISTRATION);


    /**
     * {@inheritDoc}
     */
    public RequestResult execute(final RequestContent requestContent) {
        return new RequestResult(NavigationType.REDIRECT, REGISTRATION);
    }
}
