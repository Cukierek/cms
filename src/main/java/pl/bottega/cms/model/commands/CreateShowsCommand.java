package pl.bottega.cms.model.commands;

import pl.bottega.cms.model.ShowsCalendar;

import java.time.LocalDateTime;
import java.util.Set;

public class CreateShowsCommand implements Command {

	private Long movieId;
	private Long cinemaId;
	private Set<LocalDateTime> dates;
	private ShowsCalendar showsCalendar;

	private boolean hasDates = false;
	private boolean hasShowsCalendar = false;

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(Long cinemaId) {
		this.cinemaId = cinemaId;
	}

	public Set<LocalDateTime> getDates() {
		return dates;
	}

	public void setDates(Set<LocalDateTime> dates) {
		this.dates = dates;
	}

	public ShowsCalendar getShowsCalendar() {
		return showsCalendar;
	}

	public void setShowsCalendar(ShowsCalendar showsCalendar) {
		this.showsCalendar = showsCalendar;
	}

	public boolean hasBothParameterSets() {
		return hasShowsCalendar && hasDates;
	}

	public boolean isHasDates() {
		if (dates != null) {
			if (!dates.isEmpty()) hasDates = true;
		}
		return hasDates;
	}

	public boolean isHasShowsCalendar() {
		if (showsCalendar != null) {
			hasShowsCalendar = true;
		}
		return hasShowsCalendar;
	}

	public void validate(ValidationErrors errors) {
		validatePresence(errors, "movieId", movieId);
		validatePresence(errors, "cinemaId", cinemaId);
		if (showsCalendar != null) {
			hasShowsCalendar = true;
			validatePresence(errors, "showsCalendar", showsCalendar);
		}
		if (dates != null) {
			hasDates = true;
			validatePresence(errors, "dates", dates);
		}
		boolean requestHasTooManyParameters = hasBothParameterSets();
		validateRequestFormat(errors, requestHasTooManyParameters, "dates or calendar");
	}
}
