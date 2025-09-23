package com.rc008code.hms.auth.decorators.service.decorators;

import com.rc008code.hms.auth.decorators.api.Authenticator;
import com.rc008code.hms.auth.decorators.service.model.ValidationResult;

public class LoggingAuthenticator extends AuthenticatorDecorator {
    public LoggingAuthenticator(Authenticator authenticator) {
        super(authenticator);
    }

    @Override
    public ValidationResult authenticate(String username, String password) {
        System.out.println("Logging Authenticator: " + username + " " + password + " ");
        return new ValidationResult(true, null);
    }
}
