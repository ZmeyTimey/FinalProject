package by.epam.university.command.impl;

import by.epam.university.command.Command;
import by.epam.university.command.constant.PathConstants;
import by.epam.university.command.constant.RequestConstants;
import by.epam.university.command.constant.SessionConstants;
import by.epam.university.content.NavigationType;
import by.epam.university.content.RequestContent;
import by.epam.university.content.RequestResult;
import by.epam.university.model.Certificate;
import by.epam.university.model.ExamGrade;
import by.epam.university.model.Faculty;
import by.epam.university.model.Speciality;
import by.epam.university.model.Subject;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.FacultyService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.ValidationException;
import by.epam.university.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Open user's academic data form to fill or change it.
 */
public class SendApplicationCommand implements Command {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(UserInfoEditCommand.class);

    /**
     * A path to the target page.
     */
    private static final String SUCCESS_PAGE
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.SUCCESS_PAGE);

    /**
     * A path to the error page.
     */
    private static final String ERROR_PAGE
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.ERROR_PAGE);

    /**
     * {@link FacultyService} instance.
     */
    private static final FacultyService FACULTY_SERVICE
            = ServiceFactory.getInstance().getFacultyService();

    /**
     * {@link FacultyService} instance.
     */
    private static final ApplicationService APPLICATION_SERVICE
            = ServiceFactory.getInstance().getApplicationService();

    /**
     * {@inheritDoc}
     */
    @Override
    public RequestResult execute(final RequestContent requestContent)
            throws IOException {

        String path;

        try {
            String facultyId = (String) requestContent.getSessionAttribute(
                    SessionConstants.FACULTY_ID);
            Faculty faculty = FACULTY_SERVICE.fillFacultyInfo(facultyId);

            requestContent.setAttribute(
                    RequestConstants.FIRST_SUBJECT,
                    faculty.getFirstSubject());
            requestContent.setAttribute(
                    RequestConstants.SECOND_SUBJECT,
                    faculty.getSecondSubject());
            requestContent.setAttribute(
                    RequestConstants.THIRD_SUBJECT,
                    faculty.getThirdSubject());

            Certificate certificate = createCertificate(requestContent);
            Speciality speciality = Speciality.valueOf(
                    requestContent.getParameter(RequestConstants.SPECIALITY));

            APPLICATION_SERVICE.sendApplication(speciality, certificate);

            path = SUCCESS_PAGE;

        } catch (ValidationException | ServiceException e) {
//            LOGGER.log(Level.WARN, e.getMessage());
//            path = ERROR_PAGE;
            throw new IOException(e);
        }
        return new RequestResult(NavigationType.REDIRECT, path);
    }

    /**
     * Creates {@link Certificate} instance and fills it with necessary data.
     * @param requestContent
     *            an object of {@link RequestContent}
     *            the wrapper class for {@code request}
     * @return user's certificate
     */
    private Certificate createCertificate(final RequestContent requestContent) {

        Certificate certificate = new Certificate();

        certificate.setUserId((int) requestContent.getSessionAttribute(
                SessionConstants.USER_ID));

        certificate.setSchoolGrade(
                Integer.parseInt(requestContent.getParameter(
                        RequestConstants.CERT_SCHOOL_RANGE)));

        ExamGrade[] examGrades = certificate.getExamGrades();

        examGrades[0] = new ExamGrade(
                Integer.parseInt(requestContent.getParameter(
                RequestConstants.CERT_FIRST_EXAM_RANGE)),
                (Subject) requestContent.getAttribute(
                        RequestConstants.FIRST_SUBJECT));
        examGrades[1] = new ExamGrade(
                Integer.parseInt(requestContent.getParameter(
                        RequestConstants.CERT_SECOND_EXAM_RANGE)),
                (Subject) requestContent.getAttribute(
                        RequestConstants.SECOND_SUBJECT));
        examGrades[2] = new ExamGrade(
                Integer.parseInt(requestContent.getParameter(
                        RequestConstants.CERT_THIRD_EXAM_RANGE)),
                (Subject) requestContent.getAttribute(
                        RequestConstants.THIRD_SUBJECT));
        return certificate;
    }
}
