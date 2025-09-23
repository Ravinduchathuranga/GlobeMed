package com.rc008code.hms.auth.handlers.api;

import com.rc008code.hms.auth.handlers.model.ValidationResult;

//input fields validation
public interface Validator<T> {
    ValidationResult validate(T t);
    void setNext(Validator<T> next);
}
