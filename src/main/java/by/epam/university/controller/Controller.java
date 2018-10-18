package by.epam.university.controller;

import by.epam.university.command.Command;
import by.epam.university.command.CommandFactory;
import by.epam.university.command.constant.PathConstants;
import by.epam.university.content.RequestContent;
import by.epam.university.content.RequestResult;
import by.epam.university.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller",
        urlPatterns = "/controller")
public class Controller extends HttpServlet {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Controller.class);

    /**
     * Error page path.
     */
    private static final String ERROR_PAGE
            = ConfigurationManager.getInstance()
            .getPath(PathConstants.ERROR_PAGE);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    /**
     * Gets the type of command for processing request
     * and executes this command.
     *
     * @param request
     *            an {@link HttpServletRequest} object
     *            that contains the request the
     *            client has made of the servlet
     * @param response
     *            an {@link HttpServletResponse} object
     *            that contains the response
     *            the servlet sends to the client
     * @throws ServletException
     *             if ServletException has happened during {@code forward()}
     * @throws IOException
     *             if IOException has happened during {@code SendRedirect()} or
     *             {@code forward()}
     */
    private void processRequest(final HttpServletRequest request,
                                final HttpServletResponse response)
            throws ServletException, IOException {

        RequestContent requestContent = new RequestContent();
        requestContent.extractValues(request);
        Command command
                = CommandFactory.getInstance().getCommand(requestContent);
        RequestResult requestResult = null;

        try {
            requestResult = command.execute(requestContent);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Failed to process command", e);
        }
        requestContent.insertValues(request);

        if (requestResult != null) {

            switch (requestResult.getNavigationType()) {
                case FORWARD:
                    System.out.println(requestResult.getPage());
                    forward(requestResult.getPage(), request, response);
                    break;
                case REDIRECT:
                    redirect(requestResult.getPage(), response);
                    break;
            }

        } else {
            LOGGER.log(Level.ERROR,
                    "Some troubles occurred"
                            + "while executing command,"
                            + "command might not have been found");
                redirect(ERROR_PAGE, response);
            }
    }

    private void forward(final String page,
                         final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher(page)
                .forward(request, response);
    }

    private void redirect(final String page,
                          final HttpServletResponse response)
            throws IOException {
        response.sendRedirect(page);
    }
}
