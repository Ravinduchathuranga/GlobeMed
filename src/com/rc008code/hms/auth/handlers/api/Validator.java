package com.rc008code.hms.auth.handlers.api;

import com.rc008code.hms.auth.handlers.model.ValidationResult;

public interface Validator<T> {
    ValidationResult validate(T t);
    void setNext(Validator<T> next);
}
