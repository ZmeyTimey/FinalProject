package by.epam.university.dao.impl;

import by.epam.university.dao.AbstractDAO;
import by.epam.university.dao.AuxiliaryDAO;
import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.SpecialityDAO;
import by.epam.university.dao.exception.ConnectionPoolException;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.Speciality;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Defines methods for working with 'speciality' data base.
 */
public class SpecialityDAOImpl extends AbstractDAO implements SpecialityDAO {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(UserDAOImpl.class);

////////////////////////////////////////////////////////////////////////////////

    /**
     * SQL-query for getting id of speciality by it's name.
     */
    private static final String GET_SPECIALITY_ID
            = "SELECT `id` FROM `speciality` WHERE `speciality_name`=?";

    /**
     * SQL-query for getting name of speciality by it's id.
     */
    private static final String GET_SPECIALITY_NAME
            = "SELECT `speciality_name` FROM `speciality` WHERE `id`=?";

    /**
     * SQL-query for getting specialities of adjusted faculty.
     */
    private static final String GET_FACULTY_SPECIALITIES
            = "SELECT `speciality_name` FROM `faculty`"
            + " JOIN `speciality` ON (`faculty`.`id`=`speciality`.`faculty_id`)"
            + " WHERE `faculty`.id=?";

    /**
     * The name of entity Speciality.
     */
    private static final String SPECIALITY = "speciality";

    /**
     * {@inheritDoc}
     */
    public int getSpecialityId(final String name) throws DAOException {

        AuxiliaryDAO auxiliaryDAO
                = DAOFactory.getInstance().getAuxiliaryDAO();

        return auxiliaryDAO.getIdByName(
                name, GET_SPECIALITY_ID, SPECIALITY);
    }

    /**
     * {@inheritDoc}
     */
    public String getSpecialityName(final int id) throws DAOException {

        AuxiliaryDAO auxiliaryDAO
                = DAOFactory.getInstance().getAuxiliaryDAO();

        return auxiliaryDAO.getNameById(
                id, GET_SPECIALITY_NAME, SPECIALITY);
    }

    /**
     * {@inheritDoc}
     */
    public Set<Speciality> getFacultySpecialities(final String id)
            throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Set<Speciality> specialities = new HashSet<>();

        try {
            connection = connectionProvider.obtainConnection();
            statement = connection.prepareStatement(GET_FACULTY_SPECIALITIES);
            statement.setString(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String specialityName = resultSet.getString(1);
                Speciality speciality = Speciality.valueOf(specialityName);
                specialities.add(speciality);
            }

            return specialities;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during adding certificate"
                            + "to table 'certificate'.", e);
        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }
}