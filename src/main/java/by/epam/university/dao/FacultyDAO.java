package by.epam.university.dao;

import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.Faculty;
import by.epam.university.model.Subject;

import java.util.List;

/**
 * Defines methods for working with faculty.
 */
public interface FacultyDAO {
    /**
     * Finds and returns faculty id by the name.
     * @param name name of faculty
     * @return id of faculty
     * @throws DAOException
     *             the exception during getting connection
     *             with data base or during
     *             working with data base.
     */
    String getFacultyIdByName(String name) throws DAOException;

    /**
     * Gets the names of the subjects that are submitted
     *       for admission to the faculty.
     * @param faculty {@link Faculty} instance containing faculty id
     * @return
     *       {@link Faculty} instance containing
     *       three subjects that are submitted
     *       for admission to the faculty
     * @throws DAOException
     *             the exception during getting connection
     *             with data base or during
     *             working with data base.
     */
    Faculty getSubjects(Faculty faculty) throws DAOException;

    /**
     * Gets the recruitment plan of the faculty by id.
     * @param facultyId id of the faculty
     * @return recruitment plan of the faculty
     * @throws DAOException
     *             the exception during getting connection
     *             with data base or during
     *             working with data base.
     */
    int getRecruitmentPlan(String facultyId) throws DAOException;

    /**
     * Gets all the faculty ids from the data base.
     * @return list of faculty ids
     * @throws DAOException
     *             the exception during getting connection
     *             with data base or during
     *             working with data base.
     */
    List<String> getAllFacultiesId() throws DAOException;
}
