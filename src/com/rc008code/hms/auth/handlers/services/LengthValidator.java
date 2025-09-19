package com.rc008code.hms.auth.handlers.services;

import com.rc008code.hms.auth.handlers.api.Validator;
import com.rc008code.hms.auth.handlers.model.ValidationResult;

//password validation
public class LengthValidator implements Validator<String> {
    private Validator<String> nextValidator;
    @Override
    public ValidationResult validate(String input) {
        if (input.length() < 8) {
            return new ValidationResult(false, "Password must be at least 6 characters long");
        }

        // If validation passes and there's a next validator, pass to it
        if (nextValidator != null) {
            return nextValidator.validate(input);
        }

        // If no more validators, return success
        return new ValidationResult(true, null);
    }

    @Override
    public void setNext(Validator<String> nextValidator) {
        this.nextValidator = nextValidator;
    }
}
