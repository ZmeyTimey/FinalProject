package by.epam.university.service;

import by.epam.university.model.Certificate;
import by.epam.university.service.impl.ApplicationServiceImpl;
import by.epam.university.service.impl.CertificateServiceImpl;
import by.epam.university.service.impl.FacultyServiceImpl;
import by.epam.university.service.impl.UserServiceImpl;

/**
 * Factory for creating service instances.
 */
public final class ServiceFactory {

    /**
     * The single instance of Service Factory.
     */
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    /**
     * {@link UserService} instance.
     */
    private static final UserService USER_SERVICE = new UserServiceImpl();

    /**
     * {@link FacultyService} instance.
     */
    private static final FacultyService FACULTY_SERVICE
            = new FacultyServiceImpl();

    /**
     * {@link ApplicationService} instance.
     */
    private static final ApplicationService APPLICATION_SERVICE
            = new ApplicationServiceImpl();

    /**
     * {@link CertificateService} instance.
     */
    private static final CertificateService CERTIFICATE_SERVICE
            = new CertificateServiceImpl();

    /**
     * Prevents getting more than one instance of this class.
     */
    private ServiceFactory() {
    }

    /**
     * Gets the instance of Service Factory.
     * @return the instance
     */
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Gets the User Service instance.
     * @return User Service
     */
    public UserService getUserService() {
        return USER_SERVICE;
    }

    /**
     * Gets the Application Service instance.
     * @return Application Service
     */
    public ApplicationService getApplicationService() {
        return APPLICATION_SERVICE;
    }

    /**
     * Gets the Faculty Service instance.
     * @return Faculty Service
     */
    public FacultyService getFacultyService() {
        return FACULTY_SERVICE;
    }

    /**
     * Gets the Certificate Service instance.
     * @return Certificate Service
     */
    public CertificateService getCertificateService() {
        return CERTIFICATE_SERVICE;
    }
}
