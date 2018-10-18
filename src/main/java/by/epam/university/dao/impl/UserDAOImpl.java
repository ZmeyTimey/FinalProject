package by.epam.university.dao.impl;

import by.epam.university.dao.AbstractDAO;
import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.SpecialityDAO;
import by.epam.university.dao.UserDAO;
import by.epam.university.dao.exception.ConnectionPoolException;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.Role;
import by.epam.university.model.Speciality;
import by.epam.university.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines methods for working with 'user' data base table.
 */
public class UserDAOImpl extends AbstractDAO implements UserDAO {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(UserDAOImpl.class);

////////////////////////////////////////////////////////////////////////////////

    private static final String IS_LOGIN_EXISTS
            = "SELECT EXISTS(SELECT 1 FROM `users` WHERE `login`=?)";

    private static final String IS_EMAIL_EXISTS
            = "SELECT EXISTS(SELECT 1 FROM `users` WHERE `email`=?)";

    private static final String ADD_USER
            = "INSERT INTO `users` (`login`, `password`,"
            + "`name`, `middlename`, `surname`,"
            + "`email`, `phone`, `role_id`, `faculty_abbr`,"
            + "`is_appl_sent`)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";

    private static final String SET_USER_INFO
            = "UPDATE `users` SET "
            + "`name`=?, `middlename`=?, `surname`=?, "
            + "`email`=?, `phone`=?"
            + "WHERE `id`=?";

    private static final String SET_APPLICATION_DATA
            = "UPDATE `users` SET "
            + "`school_certificate`=?, `speciality_id`=? "
            + "WHERE `id`=?";

    private static final String MARK_APPLICATION_AS_SENT
            = "UPDATE `users` SET `is_appl_sent`=?, `is_appl_confirmed`=? "
            + "WHERE `id`=?";

    private static final String SET_IS_ENLISTED
            = "UPDATE `users` SET "
            + "`is_enlisted`=? "
            + "WHERE `id`=?";

    private static final String GET_USER
            = "SELECT `users`.`id`, `login`, `role`,"
            + " `faculty_abbr`, `is_appl_sent`, `is_enlisted`"
            + " FROM `users` JOIN `user_role`"
            + " ON (`role_id`=`user_role`.`id`)"
            + " WHERE `login`=?;";

    private static final String GET_PERSONAL_INFO
            = "SELECT `name`, `middlename`, `surname`, `email`, `phone`"
            + " FROM `users` WHERE `id`=?";

    private static final String GET_PASSWORD_BY_LOGIN =
            "SELECT `password` FROM `users` WHERE `login` = ?;";

    private static final String GET_ROLE_ID
            = "SELECT `id` FROM `user_role` WHERE `role`=?";

    private static final String GET_USERS_WITH_APPLICATIONS
            = "SELECT `id`, `login`, `name`, `middlename`, `surname`,"
            + "`email`, `phone`, `faculty_abbr`,"
            + "`speciality_id`, `is_appl_confirmed`"
            + "FROM `users` WHERE `is_appl_sent`=1";

    private static final String GET_SCHOOL_CERTIFICATE
            = "SELECT `school_certificate` FROM `users` WHERE `id`=?";

    private static final String GET_SUBMITTED_USERS
            = "SELECT `id` FROM `users` "
            + "WHERE `is_appl_confirmed`=1 AND `faculty_abbr`=?";

////////////////////////////////////////////////////////////////////////////////

    private static final int ID = 1;
    private static final int LOGIN = 2;
    private static final int ROLE = 3;
    private static final int FACULTY = 4;
    private static final int IS_APPLICATION_SENT = 5;
    private static final int IS_USER_ENLISTED = 6;

    private static final int ADD_LOGIN = 1;
    private static final int ADD_PASSWORD = 2;
    private static final int ADD_NAME = 3;
    private static final int ADD_MIDDLENAME = 4;
    private static final int ADD_SURNAME = 5;
    private static final int ADD_EMAIL = 6;
    private static final int ADD_PHONE = 7;
    private static final int ADD_ROLE = 8;
    private static final int ADD_FACULTY = 9;

    private static final int APPL_SCHOOL_GRADE = 1;
    private static final int APPL_SPECIALITY = 2;
    private static final int APPL_USER_ID = 3;
    private static final int APPL_SENT = 1;
    private static final int APPL_CONFIRMED = 2;

    private static final int GET_APPL_ID = 1;
    private static final int GET_APPL_LOGIN = 2;
    private static final int GET_APPL_NAME = 3;
    private static final int GET_APPL_MIDDLENAME = 4;
    private static final int GET_APPL_SURNAME = 5;
    private static final int GET_APPL_EMAIL = 6;
    private static final int GET_APPL_PHONE = 7;
    private static final int GET_APPL_FACULTY = 8;
    private static final int GET_APPL_SPECIALITY = 9;
    private static final int GET_APPL_CONFIRMED = 10;

    private static final int NAME = 1;
    private static final int MIDDLENAME = 2;
    private static final int SURNAME = 3;
    private static final int EMAIL = 4;
    private static final int PHONE = 5;
    private static final int USER_ID = 6;

////////////////////////////////////////////////////////////////////////////////

    /**
     * Instantiates a new UserDAOImpl instance.
     */
    public UserDAOImpl() {
    }

    /**
     * Checks if the login exists already in the data base.
     * @param login searching user's login.
     * @return true if login exists in data base, false if it don't exists.
     * @throws DAOException
     *             the exception during getting connection
     *             with data base or during
     *             working with data base.
     */
    public boolean isLoginExists(final String login) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.obtainConnection();

            statement = connection.prepareStatement(IS_LOGIN_EXISTS);
            statement.setString(1, login);

            resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getBoolean(1);

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(
                    "Exception during checking whether login already taken", e);
        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmailAlreadyExists(final User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.obtainConnection();

            statement = connection.prepareStatement(IS_EMAIL_EXISTS);
            statement.setString(1, user.getEmail());

            resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getBoolean(1);

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(
                    "Exception during checking whether email already taken", e);
        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int addUser(final User user) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.obtainConnection();

            statement = connection.prepareStatement(ADD_USER,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(ADD_LOGIN, user.getLogin());
            statement.setString(ADD_PASSWORD, user.getPassword());
            statement.setString(ADD_NAME, user.getName());
            statement.setString(ADD_MIDDLENAME, user.getMiddlename());
            statement.setString(ADD_SURNAME, user.getSurname());
            statement.setString(ADD_EMAIL, user.getEmail());
            statement.setString(ADD_PHONE, user.getPhone());

            Role role = user.getRole();
            int roleId = getRoleId(role);
            statement.setInt(ADD_ROLE, roleId);

            String facultyId = user.getFacultyId();

            statement.setString(ADD_FACULTY, facultyId);
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            resultSet.next();

            return resultSet.getInt(1);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during adding user to table 'users' in DB.", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPasswordCorrect(final String login,
                                     final String password)
            throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.obtainConnection();
            statement = connection.prepareStatement(GET_PASSWORD_BY_LOGIN);

            statement.setString(1, login);
            resultSet = statement.executeQuery();
            resultSet.next();
            String correctPassword = resultSet.getString(1);

            return BCrypt.checkpw(password, correctPassword);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during checking"
                            + "password.", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserByLoginAndPassword(final String login,
                                          final String password)
            throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.obtainConnection();

            statement = connection.prepareStatement(GET_USER);
            statement.setString(1, login);

            resultSet = statement.executeQuery();
            resultSet.next();

            if (resultSet.getRow() == 0) {
                return null;
            }

            boolean isPassword = isPasswordCorrect(login, password);
            if (isPassword) {

                User user = new User();
                user.setId(resultSet.getInt(ID));
                user.setLogin(resultSet.getString(LOGIN));
                user.setRole(Role.valueOf(resultSet.getString(ROLE)));
                user.setFacultyId(resultSet.getString(FACULTY));
                user.setApplicationSent(
                        resultSet.getBoolean(IS_APPLICATION_SENT));
                if (!(resultSet.getString(IS_USER_ENLISTED) == null)) {

                    user.setEnlisted(resultSet.getBoolean(IS_USER_ENLISTED));
                }

                return user;

            } else {
                throw new DAOException("Incorrect password!");
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting user"
                    + "by login and password from DB.", e);

        } finally {
                connectionProvider.close(connection);
                connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    public User getUserPersonalInfo(final int id) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        LOGGER.debug("Getting user personal info from the DB");

        try {
            connection = connectionProvider.obtainConnection();

            statement = connection.prepareStatement(GET_PERSONAL_INFO);
            statement.setInt(1, id);
            LOGGER.debug("User's id=" + id);
            resultSet = statement.executeQuery();

            return formUser(resultSet);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting user"
                    + " personal data.", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUserPersonalInfo(final User user) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionProvider.obtainConnection();

            statement = connection.prepareStatement(SET_USER_INFO);

            statement.setString(NAME, user.getName());
            statement.setString(MIDDLENAME, user.getMiddlename());
            statement.setString(SURNAME, user.getSurname());
            statement.setString(EMAIL, user.getEmail());
            statement.setString(PHONE, user.getPhone());

            statement.setInt(USER_ID, user.getId());

            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during adding info about user"
                            + "to table 'user' in DB.", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setApplicationData(final int userId,
                              final int averageGrade,
                              final String speciality)
            throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        SpecialityDAO specialityDAO
                = DAOFactory.getInstance().getSpecialityDAO();

        try {
            connection = connectionProvider.obtainConnection();
            statement = connection.prepareStatement(SET_APPLICATION_DATA);

            int specialityId = specialityDAO.getSpecialityId(speciality);

            statement.setInt(APPL_SCHOOL_GRADE, averageGrade);
            statement.setInt(APPL_SPECIALITY, specialityId);
            statement.setInt(APPL_USER_ID, userId);
            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during adding user's application data "
                            + "to table 'user' in the data base.", e);
        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(statement);
        }
    }

    public void markApplicationAsSent(final int userId) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionProvider.obtainConnection();
            statement = connection.prepareStatement(MARK_APPLICATION_AS_SENT);
            statement.setBoolean(APPL_SENT, true);
            statement.setBoolean(APPL_CONFIRMED, false);
            statement.setInt(APPL_USER_ID, userId);
            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during adding user's application data "
                            + "to table 'user' in the data base.", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(statement);
        }
    }

    /**
     * Gets the id of user's role.
     * @param role user's role
     * @return id
     * @throws DAOException
     *             the exception during getting connection
     *             with data base or during
     *             working with data base.
     */
    private int getRoleId(final Role role) throws DAOException {

        String roleString = role.toString();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.obtainConnection();
            statement = connection.prepareStatement(GET_ROLE_ID);
            statement.setString(1, roleString);
            resultSet = statement.executeQuery();
            resultSet.next();
            resultSet.getInt(1);
            return resultSet.getInt(1);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during getting user role id"
                            + "from table 'user_role' in DB.", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<User> getUsersWithAppl() throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        SpecialityDAO specialityDAO
                = DAOFactory.getInstance().getSpecialityDAO();
        List<User> userList = new ArrayList<>();

        try {
            connection = connectionProvider.obtainConnection();
            statement = connection.prepareStatement(
                    GET_USERS_WITH_APPLICATIONS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                User user = new User();
                user.setId(resultSet.getInt(GET_APPL_ID));
                user.setLogin(resultSet.getString(GET_APPL_LOGIN));
                user.setName(resultSet.getString(GET_APPL_NAME));
                user.setMiddlename(resultSet.getString(GET_APPL_MIDDLENAME));
                user.setSurname(resultSet.getString(GET_APPL_SURNAME));
                user.setEmail(resultSet.getString(GET_APPL_EMAIL));
                user.setPhone(resultSet.getString(GET_APPL_PHONE));
                user.setFacultyId(resultSet.getString(GET_APPL_FACULTY));

                int specialityId = resultSet.getInt(GET_APPL_SPECIALITY);
                String specialityName
                        = specialityDAO.getSpecialityName(specialityId);
                user.setSpeciality(Speciality.valueOf(specialityName));

                user.setApplicationConfirmed(
                        resultSet.getBoolean(GET_APPL_CONFIRMED));

                userList.add(user);
            }

            return userList;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during getting users who sent"
                            + " applications", e);
        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }

    }

    /**
     * {@inheritDoc}
     */
    public int getSchoolCertificate(final int userId) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionProvider.obtainConnection();
            statement = connection.prepareStatement(GET_SCHOOL_CERTIFICATE);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getInt(1);

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during getting user's school grade"
                            + " from the data base", e);
        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<Integer> getSubmittedUserId(final String facultyId)
            throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Integer> userIdList = new ArrayList<>();

        try {
            connection = connectionProvider.obtainConnection();
            statement = connection.prepareStatement(
                    GET_SUBMITTED_USERS);
            statement.setString(1, facultyId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                userIdList.add(resultSet.getInt(1));
            }

            return userIdList;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(
                    "Exception during getting the number of confirmed"
                            + "applications from the data base", e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(resultSet, statement);
        }
    }

    /**
     * Creates {@link User} instance with parameters taken from result set.
     * @param resultSet is input result set.
     * @return {@link User} instance.
     * @throws SQLException when it's impossible to get any parameter
     * from result set.
     */
    private User formUser(final ResultSet resultSet) throws SQLException {

        User user = new User();
        resultSet.next();

        user.setName(resultSet.getString(NAME));
        user.setMiddlename(resultSet.getString(MIDDLENAME));
        user.setSurname(resultSet.getString(SURNAME));
        user.setEmail(resultSet.getString(EMAIL));
        user.setPhone(resultSet.getString(PHONE));

        LOGGER.debug("Operation completed");

        return user;
    }
}
