package by.epam.university.service;

import by.epam.university.model.Certificate;
import by.epam.university.model.Faculty;
import by.epam.university.model.Speciality;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.ValidationException;

/**
 * Works with user applications.
 */
public interface ApplicationService {
    /**
     * Sends entrant's application for admission to the university
     * for consideration by the administration.
     * @param speciality specialty chosen by the entrant
     * @param certificate {@link Certificate} instance that contains
     *                                       all the entrant's grades necessary
     *                                       for for admission to the university
     * @throws ValidationException
     *             the exception during validation of user parameters
     * @throws ServiceException
     *             the service layer exception
     */
    void sendApplication(Speciality speciality, Certificate certificate)
        throws ServiceException, ValidationException;
}
