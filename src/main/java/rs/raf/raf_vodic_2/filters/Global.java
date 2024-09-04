package rs.raf.raf_vodic_2.filters;

import org.apache.commons.codec.digest.DigestUtils;

public class Global {

    public static String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

}
