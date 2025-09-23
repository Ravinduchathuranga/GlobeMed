package com.rc008code.hms.auth.handlers.model;

public class ValidationResult {
    private boolean isValid;
    private String errorMessage;

    public ValidationResult(boolean isValid, String errorMessage) {
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }

    public static ValidationResult success() {
        return new ValidationResult(true, null);
    }

    public boolean isValid() {
        return isValid;
    }

    public String getErrorMessage() {
        return  errorMessage;
    }
}
