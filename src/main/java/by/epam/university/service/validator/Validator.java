package by.epam.university.service.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contains methods for validation user's input.
 */
public class Validator {

    /**
     * Logger instance for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(Validator.class);

    /**
     * The single instance of Validator.
     */
    private static final Validator INSTANCE = new Validator();

    /** Regex of valid login. */
    private static final String VALID_LOGIN_REGEX
            = "[0-9a-zA-Zа-яА-Я]{2,10}";

    /** Regex of valid password. */
    private static final String VALID_PASSWORD_REGEX = ".{5,15}";

    /**
     * Prevents getting more than one instance of this class.
     */
    private Validator() {
    }

    /**
     * Gets the instance of Validator.
     * @return instance.
     */
    public static Validator getInstance() {
        return INSTANCE;
    }

    public boolean validateLoginInputData(final String login,
                                          final String password) {

        return !(login.equals(""))
                && login.matches(VALID_LOGIN_REGEX)
                && !(password.equals(""))
                && password.matches(VALID_PASSWORD_REGEX);
    }
}
