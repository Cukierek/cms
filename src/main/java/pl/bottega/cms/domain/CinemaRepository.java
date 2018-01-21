package pl.bottega.cms.domain;

public interface CinemaRepository {

    void save(Cinema cinema);
    Cinema get(Long id);
}
