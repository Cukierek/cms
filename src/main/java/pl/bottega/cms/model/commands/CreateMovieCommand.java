package pl.bottega.cms.model.commands;

import java.util.Set;

public class CreateMovieCommand implements Command {

	private String title;
	private String description;
	private Set<String> actors;
	private Set<String> genres;
	private Integer minAge;
	private Integer length;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getActors() { return actors; }

	public void setActors(Set<String> actors) {
		this.actors = actors;
	}

	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public void validate(ValidationErrors errors) {
		validateFieldPresences(errors);
		validateFieldLengths(errors);
		validateActorsAndGenres(errors);
		validateMinAgeAndLength(errors);
	}

	private void validateFieldPresences(ValidationErrors errors) {
		validatePresence(errors, "title", title);
		validatePresence(errors, "description", description);
		validatePresence(errors, "actors", actors);
		validatePresence(errors, "genres", genres);
		validatePresence(errors, "minAge", minAge);
		validatePresence(errors, "length", length);
	}

	private void validateFieldLengths(ValidationErrors errors) {
		validateMinLength(errors, "title", title, 1);
		validateMinLength(errors, "description", description, 1);
	}

	private void validateActorsAndGenres(ValidationErrors errors) {
		if (actors != null && actors.isEmpty()) {
			errors.add("actors","At least one required");
		}
		if (actors != null) {
			for (String actor : actors) {
				if(actor == null || actor.isEmpty()) errors.add("actor","Can't be empty");
			}
		}
		if (genres != null && genres.isEmpty()) {
			errors.add("genres","At least one required");
		}
		if (genres != null) {
			for (String genre : genres) {
				if(genre == null || genre.isEmpty()) errors.add("genre","Can't be empty");
			}
		}
	}

	private void validateMinAgeAndLength(ValidationErrors errors) {
		if (minAge != null && minAge < 0)
			errors.add("minAge","Can't be less than 0");
		if (length != null && length < 0)
			errors.add("length","Can't be less than 0");
	}
}
