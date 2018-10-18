package by.epam.university.command.impl;

import by.epam.university.command.Command;
import by.epam.university.command.constant.PathConstants;
import by.epam.university.command.constant.RequestConstants;
import by.epam.university.content.NavigationType;
import by.epam.university.content.RequestContent;
import by.epam.university.content.RequestResult;
import by.epam.university.model.Faculty;
import by.epam.university.model.Subject;
import by.epam.university.util.ConfigurationManager;

/**
 * Test.
 */
public class TestCommand implements Command {

    /**
     * Path to the target page.
     */
    private static final String MAIN
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.MAIN_PAGE);

    /**
     * {@inheritDoc}
     */
    public RequestResult execute(final RequestContent requestContent) {

        Subject subject = (Subject) requestContent.getAttribute(
                RequestConstants.FIRST_SUBJECT);
        Faculty faculty = (Faculty) requestContent
                .getAttribute(RequestConstants.FACULTY);
        System.out.println(faculty.getFirstSubject());
        System.out.println(subject);

        return new RequestResult(
                NavigationType.REDIRECT, MAIN);
    }
}
