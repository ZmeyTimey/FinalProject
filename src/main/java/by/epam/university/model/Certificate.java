package by.epam.university.model;

/**
 * Stores entrant's grades for exams and school certificate.
 */
public class Certificate {

    /**
     * The number of exams that are taken for admission to the university.
     */
    private static final int NUMBER_OF_EXAMS = 3;

    /**
     * Average score of the entrant's school certificate, multiplied by 10.
     */
    private int schoolGrade;

    /**
     * Entrant's grades for exams.
     */
    private ExamGrade[] examGrades;

    /**
     * Is entrant's certificate confirmed by administrator.
     */
    private boolean isConfirmed;

    /**
     * Id of user who owns the certificate.
     */
    private int userId;

    /**
     * Instantiates a new Certificate instance.
     */
    public Certificate() {
    }

    /**
     * Gets entrant's school certificate.
     * @return school certificate
     */
    public int getSchoolGrade() {
        return schoolGrade;
    }

    /**
     * Sets entrant's school certificate.
     * @param certificate school certificate
     */
    public void setSchoolGrade(final int certificate) {
        schoolGrade = certificate;
    }

    /**
     * Gets entran't grades for exams.
     * @return exam grades
     */
    public ExamGrade[] getExamGrades() {

        if (examGrades == null) {
            examGrades = new ExamGrade[NUMBER_OF_EXAMS];
        }
        return examGrades;
    }

    /**
     * Gets is certificate confirmed by administrator.
     * @return is certificate confirmed
     */
    public boolean isConfirmed() {
        return isConfirmed;
    }

    /**
     * Sets certificate confirmed by administrator.
     * @param conf is certificate confirmed
     */
    public void setConfirmed(final boolean conf) {
        isConfirmed = conf;
    }

    /**
     * Gets user's id.
     * @return user's id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user's id.
     * @param id user's id
     */
    public void setUserId(final int id) {
        userId = id;
    }

    /**
     * Gets the number of exams that are taken
     * for admission to the university.
     * @return number of exams
     */
    public static int getNumberOfExams() {
        return NUMBER_OF_EXAMS;
    }
}
