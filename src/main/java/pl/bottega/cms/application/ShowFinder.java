package pl.bottega.cms.application;

import java.time.LocalDate;
import java.util.List;

public interface ShowFinder {
	List<ShowDto> get(Integer cinemaId, LocalDate date);

	boolean checkIfExists(Long showId);
}
