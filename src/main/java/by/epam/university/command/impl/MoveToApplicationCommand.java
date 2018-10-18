package by.epam.university.command.impl;

import by.epam.university.command.Command;
import by.epam.university.command.constant.PathConstants;
import by.epam.university.command.constant.RequestConstants;
import by.epam.university.command.constant.SessionConstants;
import by.epam.university.content.NavigationType;
import by.epam.university.content.RequestContent;
import by.epam.university.content.RequestResult;
import by.epam.university.model.Faculty;
import by.epam.university.service.FacultyService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The command for moving to the application form.
 */
public class MoveToApplicationCommand implements Command {
    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SignUpCommand.class);

    /**
     * Path to the target page.
     */
    private static final String SET_APPLICATION_DATA
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.SET_APPLICATION_DATA);

    private static final String TEST
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.TEST);
    /**
     * Path to fail page.
     */
    private static final String FAIL_PAGE
            = ConfigurationManager.getInstance().getPath(
            PathConstants.MAIN_PAGE);

    /**
     * {@link FacultyService} instance.
     */
    private static final FacultyService FACULTY_SERVICE
            = ServiceFactory.getInstance().getFacultyService();

    /**
     * {@inheritDoc}
     */
    public RequestResult execute(final RequestContent requestContent) {

        LOGGER.log(Level.DEBUG,
                "Moving to the application form menu");

        String facultyId = (String) requestContent.getSessionAttribute(
                SessionConstants.FACULTY_ID);

        try {
            Faculty faculty = FACULTY_SERVICE.fillFacultyInfo(facultyId);
            requestContent.setAttribute(RequestConstants.FACULTY, faculty);

            return new RequestResult(
                    NavigationType.FORWARD, SET_APPLICATION_DATA);

        } catch (ServiceException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.log(Level.WARN, e.getMessage());
            }
        }

        return new RequestResult(NavigationType.FORWARD, FAIL_PAGE);
    }
}
