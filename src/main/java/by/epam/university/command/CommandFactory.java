package by.epam.university.command;

import by.epam.university.command.exception.CommandParserException;
import by.epam.university.content.RequestContent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * A factory for creating Commands that process the request and form the
 * response.
 */
public final class CommandFactory {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(CommandFactory.class);

    /**
     * Creating the single instance of CommandFactory.
     */
    private static final CommandFactory INSTANCE = new CommandFactory();

    /**
     * A map of commands.
     */
    private static Map<String, Command> commands;

    static {
        try {
            commands = new CommandParser().getCommands();

        } catch (CommandParserException e) {

            if (LOGGER.isFatalEnabled()) {
                LOGGER.fatal(
                        "Fatal error during initializing"
                                + "commands from json file.", e);
            }

            throw new RuntimeException(e);
        }
    }

    /** The default name of the command. */
    private static final String DEFAULT_COMMAND_NAME = "unknownCommand";

    /**
     * Prevents getting an instance of this class.
     */
    private CommandFactory() {
    }

    /**
     * Prevents getting an instance of this class.
     */
    public static CommandFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Defines and gets the command by the parameter of the request.
     * @param requestContent
     *            an object of {@link RequestContent}
     *            the wrapper class for {@code request}
     * @return command.
     */
    public Command getCommand(final RequestContent requestContent) {

        String commandName = requestContent.getParameter("command");

        LOGGER.log(Level.DEBUG, "Command name is " + commandName);

        return commands.get(commandName);
    }
}
