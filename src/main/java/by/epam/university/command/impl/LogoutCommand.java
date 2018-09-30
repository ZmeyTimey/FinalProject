package by.epam.university.command.impl;

import by.epam.university.command.Command;
import by.epam.university.command.constant.ViewPath;
import by.epam.university.content.NavigationType;
import by.epam.university.content.RequestContent;
import by.epam.university.content.RequestResult;

import java.io.IOException;

/**
 * The command for logging out.
 */
public class LogoutCommand implements Command {

    /**
     * {@inheritDoc}
     */
    public RequestResult execute(final RequestContent requestContent)
            throws IOException {

        requestContent.setSessionToBeInvalidated(true);

        return new RequestResult(NavigationType.REDIRECT, ViewPath.LOGIN_PAGE);

    }
}
