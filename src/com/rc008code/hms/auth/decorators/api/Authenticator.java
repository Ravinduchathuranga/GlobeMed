package com.rc008code.hms.auth.decorators.api;

public interface Authenticator {
    boolean authenticate(String username, String password);
}
