package by.epam.university.controller.filter;

import by.epam.university.command.constant.SessionConstants;
import by.epam.university.model.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.Filter;

/**
 * The filter sets english locale if locale wasn't set.
 */
@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {

    /**
     * {@inheritDoc}
     * Takes locale from session, sets it to english if it's {@code null}.
     */
    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();

        if (session.getAttribute(SessionConstants.LOCALE) == null) {
            session.setAttribute(SessionConstants.LOCALE,
                    SessionConstants.DEFAULT_LOCALE);
        }

        chain.doFilter(httpRequest, httpResponse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final FilterConfig config)
            throws ServletException {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
    }
}
