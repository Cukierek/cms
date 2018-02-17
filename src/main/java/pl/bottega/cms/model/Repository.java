package pl.bottega.cms.model;

public interface Repository<T> {
	void save(T t);
	T get(Long id);

}
