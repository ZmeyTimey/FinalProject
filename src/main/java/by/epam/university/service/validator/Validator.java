package by.epam.university.service.validator;

import by.epam.university.model.Certificate;
import by.epam.university.model.ExamGrade;
import by.epam.university.model.User;
import org.apache.commons.validator.GenericValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contains methods for validation user's input.
 */
public final class Validator {

    /**
     * Logger instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Validator.class);

    /**
     * The single instance of Validator.
     */
    private static final Validator INSTANCE = new Validator();

    /** Regex corresponding to valid login. */
    private static final String VALID_LOGIN
            = "[0-9a-zA-Zа-яА-Я]{2,15}";

    /** Regex corresponding to valid password. */
    private static final String VALID_PASSWORD = ".{5,15}";
    /**
     * Regex corresponding to valid user's name, middlename and surname.
     */
    private static final String VALID_NAME = "[а-яА-Яa-zA-Z]{2,20}";
    /**
     * Regex corresponding to valid phone number.
     */
    private static final String VALID_PHONE = "[0-9]{2}\\s[0-9]{7}";

    /**
     * Maximal possible grade.
     */
    private static final int MAX_GRADE = 100;

    /**
     * Minimal passing exam grade.
     */
    private static final int MIN_EXAM_GRADE = 15;

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

    /**
     * Validates user's login and password.
     * @param login user's login
     * @param password user's password.
     * @return are login and password valid.
     */
    public boolean validateLoginInputData(final String login,
                                          final String password) {

        return !(login.equals(""))
                && login.matches(VALID_LOGIN)
                && !(password.equals(""))
                && password.matches(VALID_PASSWORD);
    }

    /**
     * Validates user's registration input data.
     * @param user is {@link User} instance containing registration data.
     * @return is registration data valid.
     */
    public boolean validateRegistrationInputData(final User user) {

        String login = user.getLogin();
        String password = user.getPassword();
        String name = user.getName();
        String middlename = user.getMiddlename();
        String surname = user.getSurname();
        String email = user.getEmail();
        String phone = user.getPhone();

        return !login.equals("") && login.matches(VALID_LOGIN)
                && !password.equals("")
                && password.matches(VALID_PASSWORD)
                && !name.equals("") && name.matches(VALID_NAME)
                && !middlename.equals("") && middlename.matches(VALID_NAME)
                && !surname.equals("") && surname.matches(VALID_NAME)
                && GenericValidator.isEmail(email)
                && !phone.equals("") && phone.matches(VALID_PHONE);
    }

    /**
     * Validates user's personal data.
     * @param user is {@link User} instance containing personal data.
     * @return is personal user's data valid.
     */
    public boolean validatePersonalUserData(final User user) {

        String name = user.getName();
        String middlename = user.getMiddlename();
        String surname = user.getSurname();
        String email = user.getEmail();
        String phone = user.getPhone();

        return !name.equals("") && name.matches(VALID_NAME)
                && !middlename.equals("") && middlename.matches(VALID_NAME)
                && !surname.equals("") && surname.matches(VALID_NAME)
                && GenericValidator.isEmail(email)
                && !phone.equals("") && phone.matches(VALID_PHONE);
    }

    /**
     * Validates user's grades.
     * @param certificate
     *          {@link Certificate} instance
     *          containing user's exam grades and school average grade.
     * @return user's grades values valid
     */
    public boolean validateCertificateData(final Certificate certificate) {

        int schoolGrade = certificate.getSchoolGrade();
        ExamGrade[] examGrades = certificate.getExamGrades();

        int firstSubjectGrade = examGrades[0].getGrade();
        int secondSubjectGrade = examGrades[1].getGrade();
        int thirdSubjectGrade = examGrades[2].getGrade();

        return schoolGrade > 0 && schoolGrade <= MAX_GRADE
                && firstSubjectGrade >= MIN_EXAM_GRADE
                && firstSubjectGrade <= MAX_GRADE
                && secondSubjectGrade >= MIN_EXAM_GRADE
                && secondSubjectGrade <= MAX_GRADE
                && thirdSubjectGrade >= MIN_EXAM_GRADE
                && thirdSubjectGrade <= MAX_GRADE;
    }
}
