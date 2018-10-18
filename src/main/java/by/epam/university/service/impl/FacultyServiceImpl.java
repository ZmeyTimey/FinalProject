package by.epam.university.service.impl;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.FacultyDAO;
import by.epam.university.dao.SpecialityDAO;
import by.epam.university.dao.UserDAO;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.Faculty;
import by.epam.university.model.Speciality;
import by.epam.university.service.FacultyService;
import by.epam.university.service.exception.ServiceException;

import java.util.List;
import java.util.Set;

/**
 * It's a class for working with faculty operations.
 */
public class FacultyServiceImpl implements FacultyService {

    /**
     * {@link DAOFactory} instance for getting objects of DAO classes.
     */
    private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
    /**
     * {@link FacultyDAO} instance.
     */
    private static final FacultyDAO FACULTY_DAO = DAO_FACTORY.getFacultyDAO();

    /**
     * {@link SpecialityDAO} instance.
     */
    private static final SpecialityDAO SPECIALITY_DAO
            = DAO_FACTORY.getSpecialityDAO();

    /**
     * {@link UserDAO} instance.
     */
    private static final UserDAO  USER_DAO
            = DAO_FACTORY.getUserDAO();

    /**
     * {@inheritDoc}
     */
    public Faculty fillFacultyInfo(final String id) throws ServiceException {

        Faculty faculty = new Faculty();
        faculty.setId(id);

        try {
            FACULTY_DAO.getSubjects(faculty);

            Set<Speciality> specialities = faculty.getSetOfSpecialities();
            specialities.addAll(SPECIALITY_DAO.getFacultySpecialities(id));

            return faculty;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Faculty fillFacultyRecruitmentInfo(final String id)
            throws ServiceException {

        Faculty faculty = new Faculty();
        faculty.setId(id);

        try {
            faculty.setRecruitmentPlan(FACULTY_DAO.getRecruitmentPlan(id));
            faculty.setSubmittedApplications(
                    USER_DAO.getSubmittedUserId(id));

            return faculty;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<String> createListOfIds() throws ServiceException {

        try {
            return FACULTY_DAO.getAllFacultiesId();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
