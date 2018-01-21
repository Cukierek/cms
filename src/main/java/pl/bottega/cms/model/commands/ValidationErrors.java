package pl.bottega.cms.model.commands;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValidationErrors {

	private Map<String, String> errors = new HashMap<>();

	public void add(String field, String error) {
		errors.put(field, error);
	}

	public boolean any() {
		return !errors.isEmpty();
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getMessage() {
		return "Invalid request parameters";
	}

}
