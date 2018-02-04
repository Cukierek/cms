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
		validatePresence(errors, "cinemaId", cinemaId);
		validatePresence(errors, "movieId", movieId);
		if (cinemaId != null) validateCinemaId(errors);
		if (movieId != null) validateMovieId(errors);
		if (dates != null) validateDates(errors);
		if (hasDates == false && calendar != null) {
			validateCalendar(errors);
		} else {
			errors.add("dates, calendar","Can't use both");
		}
	}

	private void validateCinemaId(ValidationErrors errors) {
		if (cinemaId < 1) errors.add("cinemaId","Can't be less than 1");
	}

	private void validateMovieId(ValidationErrors errors) {
		if (movieId < 1) errors.add("movieId","Can't be less than 1");
	}

	private void validateDates(ValidationErrors errors) {
		validatePresence(errors, "dates", dates);
		hasDates = true;
		if (dates.isEmpty()) errors.add("dates","Can't be empty");
		for(LocalDateTime date: dates) {
			if (date.isBefore(LocalDateTime.now())) errors.add("dates","Date can't be in the past");
		}
	}

	private void validateCalendar(ValidationErrors errors) {
		validatePresence(errors, "calendar", calendar);
		if(calendar.getFromDate() != null) {
			if(calendar.getFromDate().isBefore(LocalDateTime.now()))
				errors.add("fromDate", "Date can't be in the past");
		}
		if(calendar.getUntilDate() != null) {
			if(calendar.getUntilDate().isBefore(LocalDateTime.now()))
				errors.add("untilDate", "Date can't be in the past");
		}
		if(calendar.getFromDate() != null && calendar.getUntilDate() != null) {
			if(calendar.getFromDate().isAfter(calendar.getUntilDate()))
				errors.add("fromDate", "Can't be before untilDate");
		}
		if(calendar.getWeekDays() != null) {
			if(calendar.getWeekDays().isEmpty())errors.add("weekDays","Can't be empty");
			Set<String> weekDayStrings = new HashSet<>();
			for(DayOfWeek dayOfWeek : DayOfWeek.values()) weekDayStrings.add(dayOfWeek.toString());
			for (String weekDay : calendar.getWeekDays()) {
				if(weekDay == null) errors.add("weekDay","Can't be empty");
				if(!weekDayStrings.contains(weekDay.toUpperCase())) errors.add("weekDays", "No such day of week");
			}
		}
		if(calendar.getHours() != null) {
			if(calendar.getHours().isEmpty())errors.add("hours", "Can't be empty");
			for(LocalTime time : calendar.getHours()) if(time == null)errors.add("hour","Can't be empty");
		}

	}
}
