package com.rc008code.hms.auth.decorators.service.decorators;

import com.rc008code.hms.auth.decorators.api.Authenticator;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager extends AuthenticatorDecorator {
    public PasswordManager(Authenticator authenticator) {
        super(authenticator);
    }

    public static String hashPassword(String text) {
        String pw = BCrypt.hashpw(text, BCrypt.gensalt());
        System.out.println(pw);
        return pw;
    }

    // Check password
    @Override
    public boolean authenticate(String username, String password) {
        return authenticator.authenticate(username, hashPassword(password));
    }

}
