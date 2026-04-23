package com.rc008code.hms.auth.decorators.service.decorators;

import com.rc008code.hms.auth.decorators.api.Authenticator;

public abstract class AuthenticatorDecorator implements Authenticator {
    protected Authenticator authenticator;

    public AuthenticatorDecorator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }
}
