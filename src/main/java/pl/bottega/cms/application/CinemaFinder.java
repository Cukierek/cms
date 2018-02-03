package pl.bottega.cms.application;

import java.util.List;

public interface CinemaFinder {

	List<CinemaDto> getAll();
	CinemaDto findByCityAndName(String city, String name);
}
