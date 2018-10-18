package by.epam.university.model;

/**
 * Entrant's grade for exam for a particular subject.
 */
public class ExamGrade {

    /**
     * A grade for exam.
     */
    private int grade;

    /**
     * The subject for which the exam was taken.
     */
    private Subject subject;

    /**
     * Instantiates a new ExamGrade instance.
     * @param gd a grade
     * @param sbj a subject
     */
    public ExamGrade(final int gd,
                     final Subject sbj) {
        grade = gd;
        subject = sbj;
    }

    /**
     * Gets a grade for exam.
     * @return grade.
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Sets a grade for exam.
     * @param gd grade.
     */
    public void setGrade(final int gd) {
        grade = gd;
    }

    /**
     * Gets a subject of exam.
     * @return subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Sets a subject of exam.
     * @param subj subject
     */
    public void setSubject(final Subject subj) {
        subject = subj;
    }
}
