package by.epam.university.service;

import by.epam.university.service.impl.UserServiceImpl;

/**
 * Factory for creating service instances.
 */
public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    private static final UserService userService = new UserServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }
}
