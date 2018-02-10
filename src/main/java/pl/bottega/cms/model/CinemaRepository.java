package pl.bottega.cms.model;

import java.util.Optional;

public interface CinemaRepository extends Repository<Cinema> {
	Optional<Cinema> findByNameAndCity(String name, String city);
}
