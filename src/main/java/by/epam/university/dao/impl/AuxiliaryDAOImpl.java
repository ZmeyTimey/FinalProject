package by.epam.university.dao.impl;

import by.epam.university.dao.AbstractDAO;
import by.epam.university.dao.AuxiliaryDAO;
import by.epam.university.dao.exception.ConnectionPoolException;
import by.epam.university.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Auxiliary DAO class for getting name by id and id by name of some entity.
 */
public class AuxiliaryDAOImpl extends AbstractDAO implements AuxiliaryDAO {

    /**
     * {@inheritDoc}
     */
    public int getIdByName(final String name,
                           final String query,
                           final String entity) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.obtainConnection();

            statement = connection.prepareStatement(query);
            statement.setString(1, name);

            resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getInt(1);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during getting the id of the "
                            + entity + " " + name);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getNameById(final int id,
                              final String query,
                              final String entity) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.obtainConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getString(1);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during getting the name of the "
                            + entity + " with id=" + id);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }
}
