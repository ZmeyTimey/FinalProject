package by.epam.university.controller.filter;

import by.epam.university.command.constant.PathConstants;
import by.epam.university.util.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;

import java.io.IOException;

/**
 * Authorization filter.
 */
//@WebFilter(urlPatterns = { "/jsp/authorized/*"})
public class AuthorizationFilter implements Filter {

    /**
     * Main page path.
     */
    private static final String MAIN_PAGE
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.MAIN_PAGE);

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

//        final HttpServletRequest httpRequest
//                = (HttpServletRequest) request;
//        final HttpServletResponse httpResponse
//                = (HttpServletResponse) response;
//
//        final HttpSession session = httpRequest.getSession();
//
//        if (session.getAttribute(SessionConstants.LOGIN) == null) {
//            session.setAttribute(SessionConstants.LOG_IN_FAIL,
//                    MessageConstants.ACCESS_DENIED);
//
//            httpResponse.sendRedirect(MAIN_PAGE);
//
//        } else {
//            filterChain.doFilter(request, response);
//        }
        System.out.println("YEA!");
        filterChain.doFilter(request, response);
    }

//    /**
//     * Move user to menu.
//     * If access 'admin' move to admin menu.
//     * If access 'user' move to user menu.
//     */
//    private void moveToMenu(final HttpServletRequest request,
//                            final HttpServletResponse response,
//                            final Role role)
//            throws ServletException, IOException {
//
//        if (role.equals(Role.ADMIN)) {
//
//            request.getRequestDispatcher(
//                    ViewPath.ADMIN_MENU).forward(request, response);
//
//        } else if (role.equals(Role.ENTRANT)) {
//
//            request.getRequestDispatcher(
//                    ViewPath.ENTRANT_MENU).forward(request, response);
//
//        } else {
//
//            request.getRequestDispatcher(
//                    ViewPath.LOGIN_PAGE).forward(request, response);
//        }
//    }

    public void destroy() {
    }

    public void init(final FilterConfig config) throws ServletException {
    }
}
