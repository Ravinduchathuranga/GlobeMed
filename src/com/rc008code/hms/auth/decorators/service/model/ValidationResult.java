package com.rc008code.hms.auth.decorators.service.model;

public class ValidationResult {
    private boolean isValid;
    private String message;

    public ValidationResult(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
