package pl.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.Movie;
import pl.bottega.cms.model.MovieRepository;
import pl.bottega.cms.model.TicketPrices;
import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.CommandInvalidException;
import pl.bottega.cms.model.commands.SetTicketPricesCommand;
import pl.bottega.cms.model.commands.ValidationErrors;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Map;

@Component

public class SetTicketPricesHandler implements Handler<SetTicketPricesCommand, Void> {

    MovieRepository movieRepository;
    ValidationErrors validationErrors;

    public SetTicketPricesHandler(MovieRepository movieRepository, ValidationErrors validationErrors) {
        this.movieRepository = movieRepository;
        this.validationErrors = validationErrors;
    }

    @Override
    @Transactional
    public Void handle(SetTicketPricesCommand command) {

//        validatePrices(command.getPrices());

        Movie movie = movieRepository.get(Long.valueOf(command.getMovieId()));
        TicketPrices ticketPrices = new TicketPrices(command, validationErrors);
        ticketPrices.validatePrices();
        movie.setPrices(ticketPrices);
        movieRepository.save(movie);
        return null;
    }


    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return SetTicketPricesCommand.class;
    }
}
