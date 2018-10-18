package by.epam.university.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Faculty of the university.
 */
public class Faculty {

    /**
     * Abbreviation of the faculty.
     */
    private String id;

    /**
     * Name of the faculty.
     */
    private String facultyName;

    /**
     * The number of applicants planned to be enrolled in the faculty.
     */
    private int recruitmentPlan;

    /**
     * The list of user ids with submitted applications for this faculty.
     */
    private List<Integer> submittedApplications;

    /**
     * First of the subjects for which you had to pass exam
     * for admission to the faculty.
     */
    private Subject firstSubject;

    /**
     * Second of the subjects for which you had to pass exam
     * for admission to the faculty.
     */
    private Subject secondSubject;

    /**
     * Third of the subjects for which you had to pass exam
     * for admission to the faculty.
     */
    private Subject thirdSubject;

    /**
     * A set of specialities which are taught at the faculty.
     */
    private Set<Speciality> setOfSpecialities;

    /**
     * Instantiates a new Faculty instance.
     */
    public Faculty() {
    }

    /**
     * Instantiates a new Faculty instance.
     * @param abbr id of faculty.
     * @param name name of faculty.
     * @param plan recruitment plan of faculty.
     * @param subj1 first subject.
     * @param subj2 second subject.
     * @param subj3 third subject.
     * @param specialities a set of specialities.
     */
    public Faculty(final String abbr,
                   final String name,
                   final int plan,
                   final Subject subj1,
                   final Subject subj2,
                   final Subject subj3,
                   final Set<Speciality> specialities) {

        id = abbr;
        facultyName = name;
        recruitmentPlan = plan;
        firstSubject = subj1;
        secondSubject = subj2;
        thirdSubject = subj3;
        setOfSpecialities = specialities;
    }

    /**
     * Gets id of faculty.
     * @return id of faculty
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id of faculty.
     * @param abbr abbreviation.
     */
    public void setId(final String abbr) {
        id = abbr;
    }

    /**
     * Gets name of faculty.
     * @return name.
     */
    public String getFacultyName() {
        return facultyName;
    }

    /**
     * Sets name of faculty.
     * @param name name of faculty.
     */
    public void setFacultyName(final String name) {
        facultyName = name;
    }

    /**
     * Gets recruitment plan of faculty.
     * @return recruitment plan.
     */
    public int getRecruitmentPlan() {
        return recruitmentPlan;
    }

    /**
     * Sets recruitment plan of faculty.
     * @param plan recruitment plan.
     */
    public void setRecruitmentPlan(final int plan) {
        recruitmentPlan = plan;
    }

    /**
     * Gets the list of user ids with submitted applications.
     * @return submitted applications
     */
    public List<Integer> getSubmittedApplications() {
        return submittedApplications;
    }

    /**
     * Sets the list of user ids with submitted applications.
     * @param applications submitted applications
     */
    public void setSubmittedApplications(final List<Integer> applications) {
        submittedApplications = applications;
    }

    /**
     * Gets first exam subject.
     * @return first subject.
     */
    public Subject getFirstSubject() {
        return firstSubject;
    }

    /**
     * Sets first exam subject.
     * @param subj1 first subject.
     */
    public void setFirstSubject(final Subject subj1) {
        firstSubject = subj1;
    }

    /**
     * Gets second exam subject.
     * @return second subject.
     */
    public Subject getSecondSubject() {
        return secondSubject;
    }

    /**
     * Sets second exam subject.
     * @param subj2 second subject.
     */
    public void setSecondSubject(final Subject subj2) {
        secondSubject = subj2;
    }

    /**
     * Gets third exam subject.
     * @return subj3
     */
    public Subject getThirdSubject() {
        return thirdSubject;
    }

    /**
     * Sets third exam subject.
     * @param subj3 third subject.
     */
    public void setThirdSubject(final Subject subj3) {
        thirdSubject = subj3;
    }

    /**
     * Gets a set of specialities.
     * @return set of specialities.
     */
    public Set<Speciality> getSetOfSpecialities() {

        if (setOfSpecialities == null) {
            setOfSpecialities = new HashSet<>();
        }
        return setOfSpecialities;
    }
}
