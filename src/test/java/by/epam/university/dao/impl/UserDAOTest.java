package by.epam.university.dao.impl;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.UserDAO;
import by.epam.university.dao.connection.ConnectionPool;
import by.epam.university.dao.connection.ConnectionProvider;
import by.epam.university.dao.exception.ConnectionPoolException;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.Role;
import by.epam.university.model.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.testng.AssertJUnit.assertEquals;

public class UserDAOTest {

    private static final UserDAO userDAO
            = DAOFactory.getInstance().getUserDAO();

    private static final String GET_USER_LOGIN
            = "SELECT `login` FROM `users` WHERE `id` = ?";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        TestUtil.initializeConnectionPool();
        TestUtil.initializeDB();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        ConnectionPool.getInstance().destroy();
        ConnectionPool.getInstance().deregisterAllDrivers();
    }

    @DataProvider (name = "login")
    public static Object[][] provideLoginEmailData() {
        return new Object[][] { { "vasilyi", false }, { "user-loser", false },
                { "alex18", true }, { "ophelia", true }, };
    }

//    @Test (dataProvider = "login")
//    public void testIsLoginExists(String login,
//                                  boolean expected) throws DAOException {
//        User user = new User();
//        user.setLogin(login);
//
//        boolean loginExists = userDAO.isUserExists(user);
//
//        assertEquals(expected, loginExists);
//    }

    @Test
    public void testAddUser()
            throws DAOException, ConnectionPoolException, SQLException {

        User user = new User();

        user.setLogin("userchik");
        user.setPassword("qwa1234");
        user.setName("Алексей");
        user.setMiddlename("Иванов");
        user.setSurname("Иванович");
        user.setEmail("userchik@mail.ru");
        user.setPhone("3333333");
        user.setRole(Role.ADMIN);

        Integer idUser = userDAO.addUser(user);
        String gottenLogin = getUserLogin(idUser);

        assertEquals(user.getLogin(), gottenLogin);
    }

    private String getUserLogin(Integer id)
            throws ConnectionPoolException, SQLException {

        ConnectionProvider provider = ConnectionProvider.getInstance();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = provider.obtainConnection();
            statement = connection.prepareStatement(GET_USER_LOGIN);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString(1);

        } finally {
            provider.close(connection);
            provider.closeResources(resultSet, statement);
        }
    }
}