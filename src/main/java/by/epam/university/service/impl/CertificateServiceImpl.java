package by.epam.university.service.impl;

import by.epam.university.dao.CertificateDAO;
import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.UserDAO;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.Certificate;
import by.epam.university.service.CertificateService;
import by.epam.university.service.exception.ServiceException;

/**
 * A class for working with entrant certificates.
 */
public class CertificateServiceImpl implements CertificateService {

    /**
     * {@link DAOFactory} instance for getting objects of DAO classes.
     */
    private static DAOFactory daoFactory = DAOFactory.getInstance();

    /**
     * {@link UserDAO} instance.
     */
    private static final UserDAO USER_DAO
            = daoFactory.getUserDAO();

    /**
     * {@link CertificateDAO} instance.
     */
    private static final CertificateDAO CERTIFICATE_DAO
            = daoFactory.getCertificateDAO();

    /**
     * {@inheritDoc}
     */
    @Override
    public Certificate getCertificate(final int userId)
            throws ServiceException {

        Certificate certificate = new Certificate();

        try {
            certificate.setUserId(userId);
            CERTIFICATE_DAO.putExamGrades(certificate);

            int schoolGrade = USER_DAO.getSchoolCertificate(userId);
            certificate.setSchoolGrade(schoolGrade);

            return certificate;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
