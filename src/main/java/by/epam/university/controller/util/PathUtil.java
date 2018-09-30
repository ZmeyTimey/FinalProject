package by.epam.university.controller.util;

import by.epam.university.command.Command;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Defines static methods for working with the path to the view page.
 */
public class PathUtil {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(Command.class);

    /**
     * The name of HTTP request header that keeps information about page
     * from which request has come.
     */
    private static final String REFERER_HEADER_NAME = "referer";

    /**
     * Prevents the creation of class instance.
     */
    private PathUtil() {
        throw new AssertionError("Class contains static methods only."
                + "You should not instantiate it!");
    }

    /**
     * Gets the path of referer page (identifies the address of the webpage (i.e.
     * the URI or IRI) that linked to the resource being requested).
     *
     * @param request
     *            an {@link HttpServletRequest} object that contains the request the
     *            client has made of the servlet
     * @return the address of the webpage that linked to the resource being
     *         requested
     */
    public static String getRefererPage(final HttpServletRequest request) {
        return request.getHeader(REFERER_HEADER_NAME);
    }

    /**
     * Adds the parameter to URI.
     *
     * @param uri
     *            String that represents URI
     * @param paramName
     *            the parameter name
     * @param paramValue
     *            the parameter value
     * @return the URI with added parameter
     */
    public static URI addParameterToUri(final String uri,
                                        final String paramName,
                                        final String paramValue) {
        try {

            return new URIBuilder(uri)
                    .setParameter(paramName, paramValue).build();

        } catch (URISyntaxException e) {
            LOGGER.error("Exception during parsing uri string.", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds the parameter to the referer page.
     *
     * @param request
     *            an {@link HttpServletRequest} object that contains the request the
     *            client has made of the servlet
     * @param paramName
     *            the parameter name
     * @param paramValue
     *            the parameter value
     * @return the address of the webpage (URI) that linked to the resource being
     *         requested with added parameter
     */
    public static URI addParameterToRefererPage(
            final HttpServletRequest request,
            final String paramName,
            final String paramValue) {
        return addParameterToUri(getRefererPage(request),
                paramName, paramValue);
    }

}
