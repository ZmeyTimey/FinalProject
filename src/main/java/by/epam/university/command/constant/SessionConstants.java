package by.epam.university.command.constant;

/**
 * Specifies the names of session attributes.
 */
public class SessionConstants {

    public static final String USER_ID = "userId";
    public static final String LOGIN = "login";
    public static final String ROLE = "role";
    public static final String LOG_IN_FAIL = "errorLoginPassMessage";
    public static final String LOCALE = "local";
    public static final String DEFAULT_LOCALE = "en";
    public static final String FACULTY_ID = "facultyId";
    public static final String IS_APPLICATION_SENT = "applicationSent";
    public static final String IS_USER_ENLISTED = "enlisted";

    /**
     * Prevents getting an instance of this class.
     */
    private SessionConstants() {
        throw new AssertionError(
                "Getting instance of this class is not allowed!");
    }
}
