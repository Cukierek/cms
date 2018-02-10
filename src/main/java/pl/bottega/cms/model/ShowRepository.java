package pl.bottega.cms.model;

public interface ShowRepository {
	void save(Show show);
	Show get(Long id);
}
