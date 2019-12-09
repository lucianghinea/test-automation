package com.test.automation.testautomation.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;

public class ValidationError {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors = new ArrayList<>();

    private final String errorMessage;

    ValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    void addValidationError(String error) {
        this.errors.add(error);
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
