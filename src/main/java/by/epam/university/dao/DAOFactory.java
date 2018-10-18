package by.epam.university.dao;

import by.epam.university.dao.impl.*;

/**
 * Factory for getting instances of DAO classes.
 */
public final class DAOFactory {

    /**
     * Instance of DAOFactory.
     */
    private static final DAOFactory INSTANCE = new DAOFactory();

    /**
     * {@link UserDAO} instance.
     */
    private static final UserDAO USER_DAO = new UserDAOImpl();

    /**
     * {@link FacultyDAO} instance.
     */
    private static final FacultyDAO FACULTY_DAO = new FacultyDAOImpl();

    /**
     * {@link CertificateDAO} instance.
     */
    private static final CertificateDAO CERTIFICATE_DAO
            = new CertificateDAOImpl();

    /**
     * {@link SpecialityDAO} instance.
     */
    private static final SpecialityDAO SPECIALITY_DAO
            = new SpecialityDAOImpl();

    /**
     * {@link SubjectDAO} instance.
     */
    private static final SubjectDAO SUBJECT_DAO
            = new SubjectDAOImpl();

    /**
     * {@link AuxiliaryDAO} instance.
     */
    private static final AuxiliaryDAO AUXILIARY_DAO
            = new AuxiliaryDAOImpl();

    /**
     * Prevents getting more than one instance of this class.
     */
    private DAOFactory() {
    }

    /**
     * Gets the instance of DAO Factory.
     * @return instance.
     */
    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Gets {@code USER_DAO}.
     * @return User DAO.
     */
    public UserDAO getUserDAO() {
        return USER_DAO;
    }

    /**
     * Gets {@code FACULTY_DAO}.
     * @return Faculty DAO
     */
    public FacultyDAO getFacultyDAO() {
        return FACULTY_DAO;
    }

    /**
     * Gets {@code CERTIFICATE_DAO}.
     * @return Certificate DAO
     */
    public CertificateDAO getCertificateDAO() {
        return CERTIFICATE_DAO;
    }

    /**
     * Gets {@code SPECIALITY_DAO}.
     * @return Speciality DAO
     */
    public SpecialityDAO getSpecialityDAO() {
        return SPECIALITY_DAO;
    }

    /**
     * Gets {@code SUBJECT_DAO}.
     * @return Subject DAO
     */
    public SubjectDAO getSubjectDAO() {
        return SUBJECT_DAO;
    }

    /**
     * Gets {@code AUXILIARY_DAO}.
     * @return Auxiliary DAO
     */
    public AuxiliaryDAO getAuxiliaryDAO() {
        return AUXILIARY_DAO;
    }
}
