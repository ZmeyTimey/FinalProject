package by.epam.university.command.impl;

import by.epam.university.command.Command;
import by.epam.university.command.constant.PathConstants;
import by.epam.university.command.constant.RequestConstants;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Command for moving to the competition management menu.
 */
public class GoToCompetitionMenu implements Command {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(GoToSignUpCommand.class);

    /**
     * {@link FacultyService} instance.
     */
    private static final FacultyService FACULTY_SERVICE
            = ServiceFactory.getInstance().getFacultyService();

    /**
     * Path to the target page.
     */
    private static final String SUCCESS_PAGE
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.COMPETITION_MENU);

    /**
     * Path to fail page.
     */
    private static final String ERROR_PAGE
            = ConfigurationManager.getInstance().getPath(
            PathConstants.ADMIN);

    /**
     * {@inheritDoc}
     */
    public RequestResult execute(final RequestContent requestContent) {

        String path;

        try {
            List<String> facultyIds = FACULTY_SERVICE.createListOfIds();
            List<Faculty> facultyList = new ArrayList<>();

            for (String id : facultyIds) {
                Faculty faculty = FACULTY_SERVICE
                        .fillFacultyRecruitmentInfo(id);
                facultyList.add(faculty);
            }

            requestContent.setAttribute(
                    RequestConstants.FACULTY_LIST, facultyList);

            path = SUCCESS_PAGE;

        } catch (ServiceException e) {
            LOGGER.log(Level.WARN, e);
            path = ERROR_PAGE;
        }

        return new RequestResult(NavigationType.FORWARD, path);
    }
}
