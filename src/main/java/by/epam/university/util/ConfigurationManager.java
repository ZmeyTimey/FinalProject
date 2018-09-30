package by.epam.university.util;

import java.util.ResourceBundle;

/**
 * Helps to get messages from resource files.
 */
public class ConfigurationManager {

    private static final ConfigurationManager INSTANCE
            = new ConfigurationManager();
    private final static ResourceBundle path
            = ResourceBundle.getBundle("path");
    private final static ResourceBundle messages
            = ResourceBundle.getBundle("messages");
    private final static ResourceBundle databaseParameters
            = ResourceBundle.getBundle("db_config");

    private final static String MESSAGES_RU = "messages_ru";
    private final static String MESSAGES_EN = "messages_en";
    private final static String RUSSIAN_LOCALE = "ru";
    private final static String ENGLISH_LOCALE = "en";
    private final static String EMPTY_STRING = "";

    private ConfigurationManager() {
        throw new AssertionError("You can't create an instance"
                + "of this class using constructor");
    }

    /**
     * Gets the instance of ConfigureManager.
     * @return the instance.
     */
    public static ConfigurationManager getInstance() {
        return INSTANCE;
    }

    /**
     * Defines the page.
     *
     * @param key the key
     * @return the path
     *
     */
    public String getProperty(final String key) {
        return path.getString(key);
    }

    /**
     * Gets message.
     *
     * @param key the key
     * @return the message
     *
     */
    public String getMessage(final String key) {
        return messages.getString(key);
    }

//    /**
//     * Gets message of certain locale.
//     *
//     * @param key    the key
//     * @param locale {@link by.epam.cattery.entity.LocaleLang}
//     * @return the message
//     *
//     */
//    public String getMessage(final String key, final String locale) {
//
//        String message = EMPTY_STRING;
//
//        switch (locale) {
//            case RUSSIAN_LOCALE:
//                message = ResourceBundle.getBundle(MESSAGES_RU).getString(key);
//                break;
//            case ENGLISH_LOCALE:
//                message = ResourceBundle.getBundle(MESSAGES_EN).getString(key);
//                break;
//        }
//        return message;
//    }

    /**
     * Gets the database parameters.
     *
     * @param key the key
     * @return the database parameters
     *
     */
    public String getDatabaseParameters(final String key) {
        return databaseParameters.getString(key);
    }
}
