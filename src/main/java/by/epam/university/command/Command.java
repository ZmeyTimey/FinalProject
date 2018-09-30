package by.epam.university.command;

import by.epam.university.content.RequestContent;
import by.epam.university.content.RequestResult;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

/**
 * Defines the command method for processing the request and forming the
 * response.
 */
public interface Command {

    /**
     * Processes the request and forms the response to necessary page.
     *
     * @param requestContent
     *            an object of {@link RequestContent}
     *            the wrapper class for {@code request}
     * @throws ServletException
     *             if {@link RequestDispatcher#forward} throws this exception
     * @throws IOException
     *             if {@link RequestDispatcher#forward} throws this exception
     * @return {@link RequestResult} instance which contains the command result.
     */
    RequestResult execute(RequestContent requestContent)
            throws ServletException, IOException;
}
