package by.epam.university.service.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * This class is for encryption user's password.
 */
public class PasswordEncrypter {

    /**
     * The single instance of this class.
     */
    private static final PasswordEncrypter INSTANCE = new PasswordEncrypter();

    /**
     * Prevents getting more than one instance of this class.
     */
    private PasswordEncrypter() {
    }

    /**
     * Encrypts a password.
     * @param password a password
     * @return encrypted password
     */
    public String encryptPassword(final String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Gets the single instance of Password Encrypter.
     * @return the instance.
     */
    public static PasswordEncrypter getInstance() {
        return INSTANCE;
    }
}
