package pl.bottega.cms.model;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.commands.CreateShowsCommand;

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

	public Collection<Show> createShows(CreateShowsCommand cmd) {
		if (cmd.hasBothParameterSets())
			throw new IllegalArgumentException("You should either use calendar or dates, not both.");
		Collection<Show> shows = new LinkedList<>();
		if (cmd.isHasDates()) {

			Cinema cinema = cinemaRepository.get(cmd.getCinemaId());
			Movie movie = movieRepository.get(cmd.getMovieId());

			cmd.getDates().stream().forEach(date -> {
				Show show = new Show(cinema, movie, date);
				shows.add(show);
			});

		} else if (cmd.isHasShowsCalendar()) {

			Cinema cinema = cinemaRepository.get(cmd.getCinemaId());
			Movie movie = movieRepository.get(cmd.getMovieId());

			ShowsCalendar showsCalendar = cmd.getShowsCalendar();
			LocalDateTime fromDate = showsCalendar.getFromDate();

			long numOfDaysBetween = ChronoUnit.DAYS.between(fromDate, showsCalendar.getUntilDate());
			IntStream.iterate(0, i -> i + 1)
					.limit(numOfDaysBetween)
					.mapToObj(i -> fromDate.plusDays(i))
					.forEach(day -> {
						for (String weekDay : showsCalendar.getWeekDays()) {
							if (day.getDayOfWeek() == DayOfWeek.valueOf(weekDay.toUpperCase())) {
								for (LocalTime time : showsCalendar.getHours()) {
									LocalDate date = day.toLocalDate();
									LocalDateTime showTime = LocalDateTime.of(date, time);
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
