package com.rc008code.hms.auth.decorators.api;

import com.rc008code.hms.auth.decorators.service.model.ValidationResult;

public interface Authenticator {
    ValidationResult authenticate(String username, String password);
}
