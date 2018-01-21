package pl.bottega.cms.model;


public interface MovieRepository {
	void save(Movie movie);

	Movie get(Long id);
}
