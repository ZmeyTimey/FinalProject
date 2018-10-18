package by.epam.university.util;

import java.util.ResourceBundle;

/**
 * Helps to get MESSAGES_BUNDLE from resource files.
 */
public class ConfigurationManager {

    private static final ConfigurationManager INSTANCE
            = new ConfigurationManager();

    private static final  ResourceBundle PATH
            = ResourceBundle.getBundle("path");
    private static final  ResourceBundle MESSAGES_BUNDLE
            = ResourceBundle.getBundle("messages");

    private static final  String MESSAGES = "messages";
    private static final String MESSAGES_RU = "messages_ru";
    private static final String MESSAGES_EN = "messages_en";
    private static final String RUSSIAN_LOCALE = "ru";
    private static final String ENGLISH_LOCALE = "en";
    private static final  String EMPTY_STRING = "";

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
     * @return the PATH
     *
     */
    public String getPath(final String key) {
        return PATH.getString(key);
    }

    /**
     * Gets message.
     *
     * @param key the key
     * @return the message
     *
     */
    public String getMessage(final String key) {
        return MESSAGES_BUNDLE.getString(key);
    }

    /**
     * Gets message of certain locale.
     *
     * @param key    the key
     * @param locale {@link by.epam.university.model.Locale} with language
     * @return the message
     */
    public String getMessage(final String key, final String locale) {

        String message = EMPTY_STRING;

        message = ResourceBundle.getBundle(MESSAGES).getString(key);

                switch (locale) {
            case RUSSIAN_LOCALE:
                message = ResourceBundle.getBundle(MESSAGES_RU).getString(key);
                break;
            case ENGLISH_LOCALE:
                message = ResourceBundle.getBundle(MESSAGES_EN).getString(key);
                break;
        }
        return message;
    }

    /**
     * Gets the database parameters.
     *
     * @param key the key
     * @param dbProperties bundle with data base properties
     * @return the database parameters
     */
    public String getDatabaseParameters(final String dbProperties,
                                        final String key) {

        ResourceBundle databaseParameters
                = ResourceBundle.getBundle(dbProperties);
        return databaseParameters.getString(key);
    }
}
