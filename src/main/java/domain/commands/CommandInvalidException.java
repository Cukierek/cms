package domain.commands;

public class CommandInvalidException extends RuntimeException {

    private ValidationErrors validationErrors;

    public CommandInvalidException(ValidationErrors validationErrors) {
        this.validationErrors = validationErrors;
    }

    public ValidationErrors getValidationErrors() {
        return validationErrors;
    }
}
