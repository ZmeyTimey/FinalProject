package by.epam.university.service.impl;

import by.epam.university.dao.CertificateDAO;
import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.UserDAO;
import by.epam.university.dao.connection.ConnectionProvider;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.Certificate;
import by.epam.university.model.ExamGrade;
import by.epam.university.model.Speciality;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.ValidationException;
import by.epam.university.service.validator.Validator;

/**
 * A class for working with user applications.
 */
public class ApplicationServiceImpl implements ApplicationService {

    /**
     * {@link DAOFactory} instance for getting objects of DAO classes.
     */
    private static DAOFactory daoFactory = DAOFactory.getInstance();

    /**
     * {@link CertificateDAO} instance.
     */
    private static final CertificateDAO CERTIFICATE_DAO
            = daoFactory.getCertificateDAO();

    /**
     * {@link UserDAO} instance.
     */
    private static final UserDAO USER_DAO
            = daoFactory.getUserDAO();

    /**
     * {@inheritDoc}
     */
    public void sendApplication(final Speciality speciality,
                                final Certificate certificate)
            throws ServiceException, ValidationException {

        ConnectionProvider connectionProvider
                = ConnectionProvider.getInstance();

        if (!Validator.getInstance()
                .validateCertificateData(certificate)) {
            throw new ValidationException(
                    "User's certificate data are invalid!");
        }

        int userId = certificate.getUserId();
        int numberOfExams = Certificate.getNumberOfExams();
        int schoolGrade = certificate.getSchoolGrade();
        String specialityName = speciality.toString();

        try {
            connectionProvider.startTransaction();

            for (int i = 0; i < numberOfExams; i++) {
                ExamGrade grade = certificate.getExamGrades()[i];
                CERTIFICATE_DAO.addData(userId, grade);
            }

            USER_DAO.setApplicationData(userId, schoolGrade, specialityName);
            USER_DAO.markApplicationAsSent(userId);
            connectionProvider.commitTransaction();

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException(e);

        } finally {
            connectionProvider.endTransaction();
        }
    }
}
