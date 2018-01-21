package pl.bottega.cms.model;

import pl.bottega.cms.model.commands.CreateMovieCommand;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	private String description;

	@ElementCollection
	private Set<String> actors;

	@ElementCollection
	private Set<String> genres;

	private Integer minAge;

	private Integer length;


	public Movie() {
	}

	public Movie(CreateMovieCommand cmd) {
		this.actors = cmd.getActors();
		this.description = cmd.getDescription();
		this.genres = cmd.getGenres();
		this.length = cmd.getLength();
		this.minAge = cmd.getMinAge();
		this.title = cmd.getTitle();
	}


	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Set<String> getActors() {
		return actors;
	}

	public Set<String> getGenres() {
		return genres;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public Integer getLength() {
		return length;
	}
}
