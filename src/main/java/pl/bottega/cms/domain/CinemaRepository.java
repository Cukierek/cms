package pl.bottega.cms.domain;

import java.util.Optional;

public interface CinemaRepository {

    void save(Cinema cinema);

    Optional<Cinema> findByNameAndCity(String name, String city);
}
