package by.epam.university.command;

import by.epam.university.command.exception.CommandParserException;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class parses json file where commands are stored.
 */
public class CommandParser {

    private static final Logger LOGGER
            = LogManager.getLogger(CommandParser.class);

    private static final String JSON_FILE = "command.json";

    public Map<String, Command> getCommands()
            throws CommandParserException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(JSON_FILE)) {

            String textFromFile = IOUtils.toString(inputStream);
            JSONObject jsonObject = new JSONObject(textFromFile);

            return toMap(jsonObject);

        } catch (IOException e) {
            throw new CommandParserException("Failed to read from file", e);
        }
    }


    private Map<String, Command> toMap(JSONObject jsonObject)
            throws CommandParserException {

        Map<String, Command> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        Iterator<String> keysIterator = jsonObject.keys();

        while (keysIterator.hasNext()) {
            String key = keysIterator.next();
            Object value = jsonObject.get(key);

            map.put(key, createCommand(value));
        }

        return map;
    }


    private Command createCommand(Object value)
            throws CommandParserException {

        try {
            String commandClassString = value.toString();
            Class<?> commandClass = Class.forName(commandClassString);

            return (Command) commandClass.getDeclaredConstructor().newInstance();

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {

            throw new CommandParserException("Failed to create command", e);
        }
    }
}
