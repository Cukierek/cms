package pl.bottega.cms.model;

import org.springframework.stereotype.Component;
import pl.bottega.cms.Utils.DateUtils;
import pl.bottega.cms.model.commands.CreateShowsCommand;
import pl.bottega.cms.model.commands.ValidationErrors;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.IntStream;

@Component
public class ShowFactory {

	private CinemaRepository cinemaRepository;
	private MovieRepository movieRepository;

	public ShowFactory(CinemaRepository cinemaRepository, MovieRepository movieRepository) {
		this.cinemaRepository = cinemaRepository;
		this.movieRepository = movieRepository;
	}

	public ValidationErrors validate(CreateShowsCommand cmd) {
		ValidationErrors errors = new ValidationErrors();

		cmd.validate(errors);
		return errors;
	}

	public Collection<Show> createShows(CreateShowsCommand cmd) {
		Collection<Show> shows = new LinkedList<>();
		if (cmd.hasDates()) {

			Cinema cinema = cinemaRepository.get(cmd.getCinemaId());
			Movie movie = movieRepository.get(cmd.getMovieId());

			cmd.getDates().stream().forEach(date -> {
				LocalDateTime localDateTime = DateUtils.convertToLocalDateTime(date);
				Show show = new Show(cinema, movie, localDateTime);
				shows.add(show);
			});

		} else {

			Cinema cinema = cinemaRepository.get(cmd.getCinemaId());
			Movie movie = movieRepository.get(cmd.getMovieId());

			ShowsCalendar showsCalendar = cmd.getShowsCalendar();
			LocalDateTime fromDate = DateUtils.convertToLocalDateTime(showsCalendar.getFromDate());

			long numOfDaysBetween = ChronoUnit.DAYS.between(fromDate,  DateUtils.convertToLocalDateTime(showsCalendar.getUntilDate()));
			IntStream.iterate(0, i -> i + 1)
					.limit(numOfDaysBetween)
					.mapToObj(i -> fromDate.plusDays(i))
					.forEach(day -> {
						for (String weekDay : showsCalendar.getWeekDays()) {
							if (day.getDayOfWeek() == DayOfWeek.valueOf(weekDay.toUpperCase())) {
								for (String time : showsCalendar.getHours()) {
									LocalTime localTime = DateUtils.convertToLocalTime(time);
									LocalDate date = day.toLocalDate();
									LocalDateTime showTime = LocalDateTime.of(date, localTime);
									Show show = new Show(cinema, movie, showTime);
									shows.add(show);
								}
							}
						}
					});
		}
		return shows;
	}
}
