package com.rc008code.hms.auth.decorators.service.decorators;

import com.rc008code.hms.auth.decorators.api.Authenticator;

public abstract class AuthenticatorDecorator implements Authenticator {

    //dto logic here

    protected Authenticator authenticator;

    public AuthenticatorDecorator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public boolean authenticate(String username, String password) {
        return authenticator.authenticate(username, password);
    }
}
