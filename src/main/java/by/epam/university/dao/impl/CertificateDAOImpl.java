package by.epam.university.dao.impl;

import by.epam.university.dao.AbstractDAO;
import by.epam.university.dao.CertificateDAO;
import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.SubjectDAO;
import by.epam.university.dao.exception.ConnectionPoolException;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.Certificate;
import by.epam.university.model.ExamGrade;
import by.epam.university.model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Defines methods for working with user's certificate data base.
 */
public class CertificateDAOImpl extends AbstractDAO implements CertificateDAO {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(CertificateDAOImpl.class);

////////////////////////////////////////////////////////////////////////////////

    private static final String ADD_CERTIFICATE
            = "INSERT INTO `certificate`"
            + "(`subject_id`, `subject_grade`, `user_id`)"
            + "VALUES (?, ?, ?)";

    private static final String SET_GRADE
            = "UPDATE `certificate` SET `subject_grade`=?"
            + "WHERE `id`=?";

    private static final String GET_CERTIFICATE
            = "SELECT `subject_id`, `subject_grade`"
            + "FROM `certificate` WHERE `user_id`=?";

////////////////////////////////////////////////////////////////////////////////

    private static final int SUBJECT_ID = 1;
    private static final int EXAM_GRADE = 2;
    private static final int USER_ID = 3;

////////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     */
    public int addData(final int userId,
                        final ExamGrade grade) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        SubjectDAO subjectDAO
            = DAOFactory.getInstance().getSubjectDAO();
        try {
            connection = connectionProvider.obtainConnection();

            statement = connection.prepareStatement(ADD_CERTIFICATE,
                    Statement.RETURN_GENERATED_KEYS);

            Subject subject = grade.getSubject();
            String subjectName = subject.toString();
            int subjectId = subjectDAO.getIdByName(subjectName);

            statement.setInt(SUBJECT_ID, subjectId);
            statement.setInt(EXAM_GRADE, grade.getGrade());
            statement.setInt(USER_ID, userId);

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            resultSet.next();

            return resultSet.getInt(1);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during adding certificate"
                            + "to table 'certificate'.", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setGrade(final int id, final int grade) throws DAOException {
    }

    /**
     * {@inheritDoc}
     */
    public void putExamGrades(final Certificate certificate)
            throws DAOException {

        SubjectDAO subjectDAO
                = DAOFactory.getInstance().getSubjectDAO();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        ExamGrade[] examGrades = certificate.getExamGrades();
        int userId = certificate.getUserId();

        try {
            connection = connectionProvider.obtainConnection();
            statement = connection.prepareStatement(GET_CERTIFICATE);

            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            int counter = 0;
            while (resultSet.next()) {

                int subjectId = resultSet.getInt(SUBJECT_ID);
                int grade = resultSet.getInt(EXAM_GRADE);
                String subjectName = subjectDAO.getNameById(subjectId);

                ExamGrade examGrade
                        = new ExamGrade(grade, Subject.valueOf(subjectName));
                examGrades[counter] = examGrade;

                counter++;
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception getting certificate data"
                            + "from the data base.", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }
}
