package com.rc008code.hms.auth.decorators.service.decorators;

import com.rc008code.hms.auth.decorators.api.Authenticator;

public class LoggingAuthenticator extends AuthenticatorDecorator {
    public LoggingAuthenticator(Authenticator authenticator) {
        super(authenticator);
    }

    @Override
    public boolean authenticate(String username, String password) {
        boolean result = super.authenticate(username, password);
        System.out.println("Logging Authenticator: " + username + " " + password + " " + result);
        return result;
    }
}
