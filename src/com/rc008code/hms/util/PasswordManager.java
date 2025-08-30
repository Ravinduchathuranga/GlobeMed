package com.rc008code.hms.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager {
    public static String hasPassword(String text) {
        return BCrypt.hashpw(text, BCrypt.gensalt());
    }

}
