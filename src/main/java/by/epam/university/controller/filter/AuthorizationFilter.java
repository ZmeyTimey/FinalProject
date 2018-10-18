package by.epam.university.controller.filter;

import by.epam.university.command.constant.MessageConstants;
import by.epam.university.command.constant.PathConstants;
import by.epam.university.command.constant.SessionConstants;
import by.epam.university.util.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Authorization filter.
 */
@WebFilter(urlPatterns = { "/jsp/authorized/*"})
public class AuthorizationFilter implements Filter {

    /**
     * Main page path.
     */
    private static final String MAIN_PAGE
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.MAIN_PAGE);

    /**
     * {@inheritDoc}
     * Checks is user's login session attribute not {@code null}.
     */
    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest httpRequest
                = (HttpServletRequest) request;
        final HttpServletResponse httpResponse
                = (HttpServletResponse) response;

        final HttpSession session = httpRequest.getSession();

        if (session.getAttribute(SessionConstants.LOGIN) == null) {
            session.setAttribute(SessionConstants.LOG_IN_FAIL,
                    MessageConstants.ACCESS_DENIED);

            httpResponse.sendRedirect(MAIN_PAGE);

        } else {
            filterChain.doFilter(request, response);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final FilterConfig config)
            throws ServletException {
    }
}
