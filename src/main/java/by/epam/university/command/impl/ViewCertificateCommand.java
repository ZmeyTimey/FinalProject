package by.epam.university.command.impl;

import by.epam.university.command.Command;
import by.epam.university.command.constant.PathConstants;
import by.epam.university.command.constant.RequestConstants;
import by.epam.university.content.NavigationType;
import by.epam.university.content.RequestContent;
import by.epam.university.content.RequestResult;
import by.epam.university.model.Certificate;
import by.epam.university.service.CertificateService;
import by.epam.university.service.ServiceFactory;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The command for moving to the user's certificate view.
 */
public class ViewCertificateCommand implements Command {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(GoToSignUpCommand.class);

    /**
     * {@link CertificateService} instance.
     */
    private static final CertificateService CERTIFICATE_SERVICE
            = ServiceFactory.getInstance().getCertificateService();

    /**
     * Path to the target page.
     */
    private static final String SUCCESS_PAGE
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.VIEW_CERTIFICATE);

    /**
     * Path to fail page.
     */
    private static final String ERROR_PAGE
            = ConfigurationManager.getInstance().getPath(
            PathConstants.MAIN_PAGE);

    /**
     * {@inheritDoc}
     */
    @Override
    public RequestResult execute(final RequestContent requestContent) {
        String path;

        int userId = Integer.parseInt(
                requestContent.getParameter(RequestConstants.USER_ID));

        try {
            Certificate certificate
                    = CERTIFICATE_SERVICE.getCertificate(userId);
            requestContent.setAttribute(
                    RequestConstants.CERTIFICATE, certificate);

            path = SUCCESS_PAGE;

        } catch (ServiceException e) {
            LOGGER.log(Level.WARN, e.getMessage());
            path = ERROR_PAGE;
        }
        return new RequestResult(NavigationType.FORWARD, path);
    }
}
