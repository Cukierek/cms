package pl.bottega.cms.application;

import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.CreateReservationCommand;

public class CreateReservationHandler implements Handler<CreateReservationCommand, Void> {




    @Override
    public Void handle(CreateReservationCommand command) {
        return null;
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateReservationCommand.class;
    }
}
