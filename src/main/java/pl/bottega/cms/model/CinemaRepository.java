package pl.bottega.cms.model;

import java.util.Optional;

public interface CinemaRepository {

	void save(Cinema cinema);

	Cinema get(Long id);

	Optional<Cinema> findByNameAndCity(String name, String city);
}
