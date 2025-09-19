package com.rc008code.hms.auth.handlers.services;

import com.rc008code.hms.auth.handlers.api.Validator;
import com.rc008code.hms.auth.handlers.model.ValidationResult;
import javafx.scene.control.Alert;

// Validates that the input is not null or empty
public class NotNullValidator implements Validator<String> {
    private Validator<String> nextValidator;

    @Override
    public ValidationResult validate(String input) {
       if (input.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Login Failed");
            alert.setContentText("Invalid username or password");
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
