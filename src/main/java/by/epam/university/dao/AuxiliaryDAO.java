package by.epam.university.dao;

import by.epam.university.dao.exception.DAOException;

/**
 * Interface for realization auxiliary class for getting
 * name by id and id by name of some entity.
 */
public interface AuxiliaryDAO {

    /**
     * Gets the id of some entity by it's name.
     * @param name name
     * @param query query for getting id
     * @param entity entity
     * @return id
     * @throws DAOException
     *             the exception during getting connection
     *             with data base or during
     *             working with data base.
     */
    int getIdByName(String name, String query, String entity)
            throws DAOException;

    /**
     * Gets the name of some entity by it's id.
     * @param id id
     * @param query query for getting name
     * @param entity entity
     * @return name
     * @throws DAOException
     *             the exception during getting connection
     *             with data base or during
     *             working with data base.
     */
    String getNameById(int id, String query, String entity) throws DAOException;
}
