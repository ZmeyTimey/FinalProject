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

    private final static String MESSAGES = "messages";
//    private final static String MESSAGES_RU = "messages_ru";
//    private final static String MESSAGES_EN = "messages_en";
//    private final static String RUSSIAN_LOCALE = "ru";
//    private final static String ENGLISH_LOCALE = "en";
    private final static String EMPTY_STRING = "";

    /**
     * Prevents getting the instance of Configuration Manager
     * with a help of constructor.
     */
    private ConfigurationManager() {
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
    public String getPath(final String key) {
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
    public String getMessage(final String key, final String locale) {

        String message = EMPTY_STRING;

        message = ResourceBundle.getBundle(MESSAGES).getString(key);

        //        switch (locale) {
//            case RUSSIAN_LOCALE:
//                message = ResourceBundle.getBundle(MESSAGES_RU).getString(key);
//                break;
//            case ENGLISH_LOCALE:
//                message = ResourceBundle.getBundle(MESSAGES_EN).getString(key);
//                break;
//        }
        return message;
    }

    /**
     * Gets the database parameters.
     *
     * @param key the key
     * @return the database parameters
     *
     */
    public String getDatabaseParameters(final String dbProperties,
                                        final String key) {

        ResourceBundle databaseParameters
                = ResourceBundle.getBundle(dbProperties);
        return databaseParameters.getString(key);
    }
}
