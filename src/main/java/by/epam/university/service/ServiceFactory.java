package by.epam.university.service;

import by.epam.university.service.impl.UserServiceImpl;

/**
 * Factory for creating service instances.
 */
public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private static final UserService USER_SERVICE = new UserServiceImpl();

    /**
     * Prevents getting more than one instance of this class.
     */
    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }
}
