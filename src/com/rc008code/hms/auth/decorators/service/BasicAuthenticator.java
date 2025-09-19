package com.rc008code.hms.auth.decorators.service;

import com.rc008code.hms.auth.decorators.api.Authenticator;

public class BasicAuthenticator implements Authenticator {

    //dao logic here

    @Override
    public boolean authenticate(String username, String password) {
        System.out.println("Basic Authenticator: " + username + " " + password + "");
        return false;
    }
}
