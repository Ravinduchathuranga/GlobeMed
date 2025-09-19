package com.rc008code.hms.auth.handlers.services;

import com.rc008code.hms.auth.handlers.api.Validator;
import com.rc008code.hms.auth.handlers.model.ValidationResult;

public class NotNullValidator implements Validator<String> {
    private Validator<String> nextValidator;

    @Override
    public ValidationResult validate(String input) {
        if (input == null) {
            return new ValidationResult(false, "Input cannot be null");
        }else if (input.isEmpty()) {
            return new ValidationResult(false, "Input cannot be empty");
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
