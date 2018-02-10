package pl.bottega.cms.model.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.bottega.cms.model.ShowsCalendar;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class CreateShowsCommand implements Command {

	private Long movieId, cinemaId;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
	private Set<LocalDateTime> dates;
	private ShowsCalendar calendar;

	private boolean hasDates = false;
	private boolean hasCalendar = false;

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

	public ShowsCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(ShowsCalendar calendar) {
		this.calendar = calendar;
	}

	public boolean hasDates() {
		return hasDates;
	}

	public void validate(ValidationErrors errors) {
		validateCinemaId(errors);
		validateMovieId(errors);

		if (dates != null) hasDates = true;
		if (calendar != null) hasCalendar = true;

		if (hasDates && hasCalendar) {
			errors.add("dates or calendar", "Can't use both");
		} else if (hasDates && !hasCalendar) {
			validateDates(errors);
		} else if (!hasDates && hasCalendar) {
			validateCalendar(errors);
		} else if (!hasDates && !hasCalendar) {
			errors.add("dates or calendar", "At least one is required");
		}
	}

	private void validateCinemaId(ValidationErrors errors) {
		validatePresence(errors, "cinemaId", cinemaId);
		if (cinemaId < 1) errors.add("cinemaId", "Can't be less than 1");
	}

	private void validateMovieId(ValidationErrors errors) {
		validatePresence(errors, "movieId", movieId);
		if (movieId < 1) errors.add("movieId", "Can't be less than 1");
	}

	private void validateDates(ValidationErrors errors) {
		validatePresence(errors, "dates", dates);
		if (dates.isEmpty()) errors.add("dates", "Can't be empty");
		for (LocalDateTime date : dates) {
			if (date.isBefore(LocalDateTime.now())) errors.add("dates", "Date can't be in the past");
		}
	}

	private void validateCalendar(ValidationErrors errors) {
		validatePresence(errors, "calendar", calendar);

		if (calendar.getFromDate() == null) {
			errors.add("fromDate", "Can't be empty");
		} else {
			if (calendar.getFromDate().isBefore(LocalDateTime.now()))
				errors.add("fromDate", "Date can't be in the past");
		}

		if (calendar.getUntilDate() == null) {
			errors.add("untilDate", "Can't be empty");
		} else {
			if (calendar.getUntilDate().isBefore(LocalDateTime.now()))
				errors.add("untilDate", "Date can't be in the past");
		}

		if (calendar.getFromDate() != null && calendar.getUntilDate() != null) {
			if (calendar.getFromDate().isAfter(calendar.getUntilDate()))
				errors.add("fromDate", "Can't be before untilDate");
		}

		if (calendar.getWeekDays() == null) {
			errors.add("weekDays", "Can't be empty");
		} else {
			if (calendar.getWeekDays().isEmpty()) errors.add("weekDays", "Can't be empty");
			Set<String> weekDayStrings = new HashSet<>();
			for (DayOfWeek dayOfWeek : DayOfWeek.values()) weekDayStrings.add(dayOfWeek.toString());
			for (String weekDay : calendar.getWeekDays()) {
				if (weekDay == null) errors.add("weekDay", "Can't be empty");
				if (!weekDayStrings.contains(weekDay.toUpperCase())) errors.add("weekDays", "No such day of week");
			}
		}

		if (calendar.getHours() == null) {
			errors.add("hours", "Can't be empty");
		} else {
			if (calendar.getHours().isEmpty()) errors.add("hours", "Can't be empty");
			for (LocalTime time : calendar.getHours()) if (time == null) errors.add("hour", "Can't be empty");
		}
	}
}
