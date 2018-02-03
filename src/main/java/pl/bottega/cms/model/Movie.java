package pl.bottega.cms.model;

import pl.bottega.cms.model.commands.AddMovieCommand;

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

	public Movie(String title, String description, Set<String> actors, Set<String> genres, Integer minAge, Integer length) {
		this.title = title;
		this.description = description;
		this.actors = actors;
		this.genres = genres;
		this.minAge = minAge;
		this.length = length;
	}


		public Movie(AddMovieCommand amd) {
		this.actors = amd.getActors();
		this.description = amd.getDescription();
		this.genres = amd.getGenres();
		this.length = amd.getLength();
		this.minAge = amd.getMinAge();
		this.title = amd.getTitle();
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
