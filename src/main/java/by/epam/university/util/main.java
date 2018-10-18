package by.epam.university.util;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.UserDAO;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.service.util.PasswordEncrypter;


public class main {
    public static void main(String[] args) {
        PasswordEncrypter passwordEncrypter = PasswordEncrypter.getInstance();
        System.out.println(passwordEncrypter.encryptPassword("1234567"));
    }
}
