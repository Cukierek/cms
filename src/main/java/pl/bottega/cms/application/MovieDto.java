package pl.bottega.cms.application;

import pl.bottega.cms.model.Movie;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieDto {

    private Long id;
    private String title;
    private String description;
    private Set<String> actors;
    private Set<String> genres;
    private Integer minAge;
    private Integer lenght;
    private List<ShowDto> shows;

    public MovieDto(){}

    public MovieDto(Long id, String title){
        this.id = id;
        this.title = title;
    }



    public MovieDto(Long id, String title, String description, Set<String> actors, Set<String> genres, Integer minAge, Integer lenght, List<ShowDto> shows) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.actors = actors;
        this.genres = genres;
        this.minAge = minAge;
        this.lenght = lenght;
        this.shows = shows;
    }

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.actors = movie.getActors();
        this.genres = movie.getGenres();
        this.minAge = movie.getMinAge();
        this.lenght = movie.getLength();
        this.shows = movie.getShows().stream()
                .map(ShowDto::new)
                .sorted(Comparator.comparing(ShowDto::getTime))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    public List<ShowDto> getShows() {
        return shows;
    }

    public void setShows(List<ShowDto> shows) {
        this.shows = shows;
    }
}
