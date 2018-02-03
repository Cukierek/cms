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

	public Set<String> getActors() {
		return actors;
	}

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
		validatePresence(errors, "title", title);
		validateMinLength(errors, "title", title, 1);
		validatePresence(errors, "description", description);
		validateMinLength(errors, "description", description, 1);
		validatePresence(errors, "actors", actors);
		validateMinLength(errors,"actors", String.valueOf(actors), 1);
		validatePresence(errors, "genres", genres);
		validateMinLength(errors, "genres", String.valueOf(genres), 1);
		validatePresence(errors, "minAge", minAge);
		validateMinLength(errors, "minAge", String.valueOf(minAge), 1);
		validatePresence(errors, "length", length);
		validateMinLength(errors, "length", String.valueOf(length), 1);
	}
}
