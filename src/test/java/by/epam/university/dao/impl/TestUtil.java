package by.epam.university.dao.impl;

import by.epam.university.dao.connection.ConnectionPool;
import by.epam.university.dao.connection.ConnectionProvider;
import by.epam.university.dao.exception.ConnectionPoolException;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class TestUtil {

    private static final String DB_PROPERTIES_FILE = "db_test";

    private static final String RECREATE_DB_SQL_FILE
            = "selection_committee_test.sql";

    private TestUtil() {
        throw new AssertionError(
                "Class contains static methods only. You should not instantiate it!");
    }

    public static void initializeConnectionPool() throws ConnectionPoolException {
        ConnectionPool.getInstance().initialize(DB_PROPERTIES_FILE);
    }

    public static void initializeDB() throws ConnectionPoolException, SQLException {
        ConnectionProvider provider = ConnectionProvider.getInstance();
        Connection connection = null;

        try {
            connection = provider.obtainConnection();
            ScriptRunner scriptRunner = new ScriptRunner(connection);

            ClassLoader classLoader = UserDAOTest.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(RECREATE_DB_SQL_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            scriptRunner.runScript(reader);
        } finally {
            provider.close(connection);
        }

    }

}
