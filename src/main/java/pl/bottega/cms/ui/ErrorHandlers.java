package pl.bottega.cms.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.bottega.cms.infrastructure.NoSuchEntityException;
import pl.bottega.cms.model.commands.CommandInvalidException;

@ControllerAdvice
public class ErrorHandlers {

	@ResponseStatus(code = HttpStatus.NOT_FOUND,
			reason = "Entity with given id does not exist")
	@ExceptionHandler(NoSuchEntityException.class)
	public void handleEntityNotFound() {
	}

	@ExceptionHandler(CommandInvalidException.class)
	public ResponseEntity handleInvalidCommand(CommandInvalidException ex) {
		return new ResponseEntity(ex.getValidationErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
}