package by.epam.university.runner;

import java.sql.*;

public class Main {

    private static final String DB_DRIVER_CLASS
            = "com.mysql.cj.jdbc.Driver";

    private static final String DB_URL
            = "jdbc:mysql://localhost:3306/selection_committee"
            + "?useUnicode=true&characterEncoding=UTF-8"
            + "&useSSL=false"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final String SELECT_USER
            = "SELECT * FROM `users` WHERE `id`=1";
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(DB_DRIVER_CLASS).newInstance();
            connection = DriverManager.getConnection(
                    DB_URL,DB_USER, DB_PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_USER);
            resultSet.next();
            System.out.println(resultSet.getString("login"));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
