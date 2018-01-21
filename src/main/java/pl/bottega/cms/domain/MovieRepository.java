package pl.bottega.cms.domain;


public interface MovieRepository {
    void save(Movie movie);
    Movie get(Long id);
}
