package by.epam.university.dao.impl;

import by.epam.university.dao.AbstractDAO;
import by.epam.university.dao.FacultyDAO;
import by.epam.university.dao.exception.ConnectionPoolException;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.Faculty;
import by.epam.university.model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines methods for working with 'user' data base table.
 */
public class FacultyDAOImpl extends AbstractDAO implements FacultyDAO {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(UserDAOImpl.class);

////////////////////////////////////////////////////////////////////////////////

    private static final String GET_FACULTY_ID_BY_NAME
        = "SELECT `id` FROM `faculty` WHERE `faculty_name`=?";

    private static final String GET_FIRST_SUBJECT
            = "SELECT `subject_name` FROM `faculty`"
            + " JOIN `subject` ON (`first_subject_id`=`subject`.`id`)"
            + " WHERE `faculty`.`id`=?";

    private static final String GET_SECOND_SUBJECT
            = "SELECT `subject_name` FROM `faculty`"
            + " JOIN `subject` ON (`second_subject_id`=`subject`.`id`)"
            + " WHERE `faculty`.`id`=?";

    private static final String GET_THIRD_SUBJECT
            = "SELECT `subject_name` FROM `faculty`"
            + " JOIN `subject` ON (`third_subject_id`=`subject`.`id`)"
            + " WHERE `faculty`.`id`=?";

    private static final String GET_RECRUITMENT_PLAN
            = "SELECT `recruitment_plan` FROM 'faculty' WHERE `id`=?";

    private static final String GET_ALL_FACULTY_ID
            = "SELECT `id` FROM `faculty`";

////////////////////////////////////////////////////////////////////////////////

    private static final int ID_FACULTY = 1;
    private static final int FACULTY_NAME = 2;
    private static final int RECRUITMENT_PLAN = 3;

////////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     */
    public String getFacultyIdByName(final String name) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.obtainConnection();

            statement = connection.prepareStatement(GET_FACULTY_ID_BY_NAME);

            statement.setString(1, name);
            resultSet = statement.executeQuery();
            resultSet.next();

            if (resultSet.getRow() == 0) {
                return null;
            }

            return resultSet.getString(1);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting faculty"
                    + " by name from DB.", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Faculty getSubjects(final Faculty faculty) throws DAOException {

        Connection connection = null;

        String id = faculty.getId();

        try {
            connection = connectionProvider.obtainConnection();

            Subject firstSubject
                    = getSubject(id, connection, GET_FIRST_SUBJECT);
            Subject secondSubject
                    = getSubject(id, connection, GET_SECOND_SUBJECT);
            Subject thirdSubject
                    = getSubject(id, connection, GET_THIRD_SUBJECT);

            faculty.setFirstSubject(firstSubject);
            faculty.setSecondSubject(secondSubject);
            faculty.setThirdSubject(thirdSubject);

            return faculty;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting subjects"
                    + " of the faculty.", e);
        } finally {
            connectionProvider.close(connection);
        }
    }

    /**
     * {@inheritDoc}
     */
    public int getRecruitmentPlan(final String facultyId) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.obtainConnection();

            statement = connection.prepareStatement(GET_RECRUITMENT_PLAN);
            statement.setString(1, facultyId);
            resultSet = statement.executeQuery();
            resultSet.next();
            int recruitmentPlan = resultSet.getInt(1);

            return recruitmentPlan;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during getting"
                            + "recruitment plan of the faculty", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getAllFacultiesId() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<String> facultyIdList = new ArrayList<>();

        try {
            connection = connectionProvider.obtainConnection();

            statement = connection.prepareStatement(GET_ALL_FACULTY_ID);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                facultyIdList.add(resultSet.getString(1));
            }

            return facultyIdList;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during getting"
                            + "recruitment plan of the faculty", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * Gets name of subject from data base.
     * @param id faculty id
     * @param connection connection to the data base
     * @param query the query for getting subject
     * @return {@link Subject} instance
     * @throws SQLException when it's impossible to execute a query
     */
    private Subject getSubject(final String id,
                               final Connection connection,
                               final String query)
            throws SQLException {

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        if (resultSet.getRow() == 0) {
            return null;
        }

        Subject subject = Subject.valueOf(resultSet.getString(1));
        connectionProvider.closeResources(resultSet, statement);

        return subject;
    }
}
