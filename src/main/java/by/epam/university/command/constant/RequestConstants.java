package by.epam.university.command.constant;

/**
 * Stores the names of request attributes.
 */
public final class RequestConstants {

    public static final String USER = "user";
    public static final String USER_ID = "userId";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_NAME = "name";
    public static final String USER_MIDDLENAME = "middlename";
    public static final String USER_SURNAME = "surname";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE = "phone";

    public static final String FACULTY = "faculty";
    public static final String FACULTY_NAME = "facultyName";
    public static final String FIRST_SUBJECT = "firstSubject";
    public static final String SECOND_SUBJECT = "secondSubject";
    public static final String THIRD_SUBJECT = "thirdSubject";
    public static final String SPECIALITIES = "setOfSpecialities";
    public static final String SPECIALITY = "speciality";
    public static final String FACULTY_LIST = "facultyList";

    public static final String CERTIFICATE = "certificate";
    public static final String CERT_SCHOOL_RANGE = "schoolCertificate";
    public static final String CERT_FIRST_EXAM_RANGE = "firstExamRange";
    public static final String CERT_SECOND_EXAM_RANGE = "secondExamRange";
    public static final String CERT_THIRD_EXAM_RANGE = "thirdExamRange";

    public static final String USER_LIST = "userList";

    /**
     * Prevents getting an instance of this class.
     */
    private RequestConstants() {
        throw new AssertionError(
                "Getting instance of this class is not allowed!");
    }
}
