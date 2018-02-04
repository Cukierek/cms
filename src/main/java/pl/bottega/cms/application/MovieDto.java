package pl.bottega.cms.application;

import java.util.List;

public class MovieDto {

    private String title;
    private String description;
    private List<String> actors;
    private List<String> genres;
    private Integer minAge;
    private Integer lenght;
    private List<ShowDto> shows;

    public MovieDto(String title, String description, List<String> actors, List<String> genres, Integer minAge, Integer lenght, List<ShowDto> shows) {
        this.title = title;
        this.description = description;
        this.actors = actors;
        this.genres = genres;
        this.minAge = minAge;
        this.lenght = lenght;
        this.shows = shows;
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

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
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
