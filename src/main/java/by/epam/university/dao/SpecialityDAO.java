package by.epam.university.dao;

import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.Faculty;
import by.epam.university.model.Speciality;

import java.util.Set;

/**
 * Defines methods for working with specialities.
 */
public interface SpecialityDAO {

    /**
     * Gets id of speciality by it's name.
     * @param name name of speciality
     * @return id of speciality
     * @throws DAOException
     *             the exception during getting connection
     *             with data base or during
     *             working with data base.
     */
     int getSpecialityId(String name) throws DAOException;

    /**
     * Gets the name of the speciality by id.
     * @param id id of the speciality
     * @return name of the speciality
     * @throws DAOException
     *             the exception during getting connection
     *             with data base or during
     *             working with data base.
     */
     String getSpecialityName(int id) throws DAOException;

    /**
     * Gets all the specialities of the faculty with gived id.
     * @param facultyId id of faculty
     * @return a set of specialities
     * @throws DAOException
     *             the exception during getting connection
     *             with data base or during
     *             working with data base.
     */
     Set<Speciality> getFacultySpecialities(String facultyId)
             throws DAOException;
}
