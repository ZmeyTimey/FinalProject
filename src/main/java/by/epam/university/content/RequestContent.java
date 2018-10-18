package by.epam.university.content;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Accumulates the data from request that are extracted
 * into RequestContent then after command
 * has been executed inserts the data into request.
 */
public class RequestContent {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RequestContent.class);

    private static final String REFERER = "referer";
    private static final String MULTIPART = "multipart/form-data";
    private static final String PAGINATION_PAGE_COUNT = "pageCount";
    private static final String PAGINATION_PAGE = "page";

    private Map<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;
    private Collection<Part> requestParts;
    private String page;
    private String referer;
    private boolean sessionToBeInvalidated;

    /**
     * Instantiates a new RequestContent instance.
     */
    public RequestContent() {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
        page = null;
    }

    /**
     * Extracts all the values from request.
     * @param request request.
     */
    public void extractValues(final HttpServletRequest request) {

        extractAttributes(request);
        extractParameters(request);
        extractSessionAttributes(request);
        extractParts(request);
        referer = request.getHeader(REFERER);
        sessionToBeInvalidated = false;
    }

    /**
     * Inserts attributes into request.
     * @param request request
     */
    public void insertValues(final HttpServletRequest request) {

        insertAttributes(request);
        insertSessionAttributes(request);
    }

    /**
     * Extracts attributes from request.
     * @param request request
     */
    private void extractAttributes(final HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getAttributeNames();

        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            requestAttributes.put(
                    attributeName, request.getAttribute(attributeName));
        }
    }

    /**
     * Extracts parameters from request.
     * @param request request
     */
    private void extractParameters(final HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            requestParameters.put(
                    parameterName, request.getParameterValues(parameterName));
        }
    }

    /**
     * Extract attributes from session.
     * @param request request
     */
    private void extractSessionAttributes(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Enumeration<String> sessionAttributeNames
                    = session.getAttributeNames();

            while (sessionAttributeNames.hasMoreElements()) {
                String sessionAttributeName
                        = sessionAttributeNames.nextElement();
                sessionAttributes.put(
                        sessionAttributeName,
                        session.getAttribute(sessionAttributeName));
            }
        }
    }

    /**
     * Extracts parts from request.
     * @param request request
     */
    private void extractParts(final HttpServletRequest request) {

        try {
            if ((request.getContentType() != null)
                    && (request.getContentType()
                    .toLowerCase().contains(MULTIPART))) {
                requestParts = request.getParts();
            }

        } catch (ServletException | IOException e) {

            if (LOGGER.isErrorEnabled()) {
                LOGGER.log(Level.ERROR, "Failed to get parts from request", e);
            }
        }
    }

    /**
     * Inserts attributes into request.
     * @param request request
     */
    private void insertAttributes(final HttpServletRequest request) {

        for (Map.Entry<String, Object> entry : requestAttributes.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Inserts attributes into session.
     * @param request request
     */
    private void insertSessionAttributes(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {

            for (Map.Entry<String,
                    Object> entry : sessionAttributes.entrySet()) {
                session.setAttribute(entry.getKey(), entry.getValue());
            }
            if (sessionToBeInvalidated) {
                session.invalidate();
            }
        }
    }

    /**
     * Sets request attribute with a given name.
     * @param name name of attribute.
     * @param value value of attribute.
     */
    public void setAttribute(final String name,
                             final Object value) {
        requestAttributes.put(name, value);
    }

    /**
     * Sets session attribute with a given name.
     * @param name name of attribute.
     * @param value value of attribute.
     */
    public void setSessionAttribute(final String name,
                                    final Object value) {
        sessionAttributes.put(name, value);
    }

    /**
     * Gets request attribute by name.
     * @param name name of attribute.
     * @return attribute.
     */
    public Object getAttribute(final String name) {
        return requestAttributes.get(name);
    }

    /**
     * Gests session attribute by name.
     * @param name name of attribute.
     * @return attribute.
     */
    public Object getSessionAttribute(final String name) {
        return sessionAttributes.get(name);
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String pg) {
        page = pg;
    }

    public void setPaginationParameters(final int pageCount,
                                        final int pg) {
        requestAttributes.put(PAGINATION_PAGE_COUNT, pageCount);
        requestAttributes.put(PAGINATION_PAGE, pg);
    }

    /**
     * Gets current page.
     * @return page.
     */
    public String getCurrentPage() {
        return referer;
    }

    public String[] getAllParameters(final String name) {
        return requestParameters.get(name);
    }

    public String getParameter(final String name) {
        String[] parameters = requestParameters.get(name);

        if (parameters != null) {
            return parameters[0];
        }
        return null;
    }

    public Part getPart(final String name) {

        Part part = null;

        if (requestParts != null) {

            for (Part requestPart : requestParts) {

                if (requestPart.getName().equals(name)) {
                    part = requestPart;
                    break;
                }
            }
        }
        return part;
    }


    public boolean isSessionToBeInvalidated() {
        return sessionToBeInvalidated;
    }


    public void setSessionToBeInvalidated(final boolean sessionInvalid) {
        sessionToBeInvalidated = sessionInvalid;
    }
}
