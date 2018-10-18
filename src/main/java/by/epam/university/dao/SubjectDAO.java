package by.epam.university.dao;

import by.epam.university.dao.exception.DAOException;

/**
 * Defines methods for working with subjects.
 */
public interface SubjectDAO {

    /**
     * Gets the name of subject by id.
     * @param id id of the subject
     * @return name
     * @throws DAOException
     *             the exception during getting connection with data base
     *             or during working with data base
     */
    String getNameById(int id) throws DAOException;

    /**
     * Gets the id of the subject by name.
     * @param name name of the subject
     * @return id
     * @throws DAOException
     *             the exception during getting connection with data base
     *             or during working with data base
     */
    int getIdByName(String name) throws DAOException;
}
