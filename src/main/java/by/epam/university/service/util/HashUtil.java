package by.epam.university.service.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

    private HashUtil() {
        throw new AssertionError("Class contains static methods only."
                + "You should not instantiate it!");
    }

    public static String toHash(final String value) {
        return DigestUtils.sha256Hex(value);
    }

}
