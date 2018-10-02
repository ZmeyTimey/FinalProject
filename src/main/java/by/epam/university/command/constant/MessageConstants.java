package by.epam.university.command.constant;

/**
 * Stores the names of messages attributes.
 */
public class MessageConstants {

    public static final String WRONG_LOGIN_OR_PASSWORD = "message.login_error";
    public static final String INVALID_INPUT = "message.input_invalid";
    public static final String ACCESS_DENIED = "message.access_denied";

    /**
     * Prevents getting more than one instance of this class.
     */
    private MessageConstants() {
        throw new AssertionError(
                "Getting instance of this class is not allowed!");
    }
}
