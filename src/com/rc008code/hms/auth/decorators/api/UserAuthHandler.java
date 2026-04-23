package com.rc008code.hms.auth.decorators.api;

import com.rc008code.hms.auth.decorators.service.model.ValidationResult;

public abstract class UserAuthHandler {
    protected UserAuthHandler nextHandler;

    public void setNextHandler(UserAuthHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract ValidationResult authenticate(String username, String password);
}
