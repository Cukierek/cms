package pl.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.*;
import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.CommandInvalidException;
import pl.bottega.cms.model.commands.CreateReservationCommand;
import pl.bottega.cms.model.commands.ValidationErrors;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CreateReservationHandler implements Handler<CreateReservationCommand, Long> {

    private ShowRepository showRepository;
    private ReservationRepository reservationRepository;
    private ValidationErrors errors;


    public CreateReservationHandler(ShowRepository showRepository, ReservationRepository reservationRepository, ValidationErrors errors) {
        this.showRepository = showRepository;
        this.reservationRepository = reservationRepository;
        this.errors = errors;
    }

    @Override
    @Transactional
    public Long handle(CreateReservationCommand command) {
        Reservation reservation = new Reservation(command);
        List<Reservation> currentReservations = reservationRepository.getReservations(command.getShowId());
        CinemaHall cinemaHall = new CinemaHall(currentReservations, command, errors, showRepository);
        cinemaHall.validateTickets();

        reservationRepository.save(reservation);

        return reservation.getId();
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateReservationCommand.class;
    }


}

