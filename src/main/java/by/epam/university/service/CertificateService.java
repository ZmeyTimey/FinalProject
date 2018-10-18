package by.epam.university.service;

import by.epam.university.model.Certificate;
import by.epam.university.service.exception.ServiceException;

/**
 * Works with user certificates.
 */
public interface CertificateService {
    /**
     * Gets {@link Certificate} instance that contains user's grades.
     * @param userId user's id
     * @throws ServiceException
     *             the service layer exception
     * @return certificate
     */
    Certificate getCertificate(int userId) throws ServiceException;
}
