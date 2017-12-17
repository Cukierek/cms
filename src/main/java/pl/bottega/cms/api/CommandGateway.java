package pl.bottega.cms.api;

import pl.bottega.cms.domain.commands.Command;
import pl.bottega.cms.domain.commands.CommandInvalidException;
import pl.bottega.cms.domain.commands.ValidationErrors;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


import java.util.Map;
import java.util.Optional;

@Component
public class CommandGateway {

    private ApplicationContext applicationContext;

    public CommandGateway(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void execute(Command command) {
        validate(command);
        Handler handler = handlerFor(command);
        handler.handle(command);
    }

    private void validate(Command command) {
        ValidationErrors validationErrors = new ValidationErrors();
        command.validate(validationErrors);
        if(validationErrors.any())
            throw new CommandInvalidException(validationErrors);
    }

    private Handler handlerFor(Command command) {
        Map<String, Handler> handlers = applicationContext.getBeansOfType(Handler.class);
        Optional<Handler> handlerOptional = handlers.values().stream().
                filter((h) -> h.canHandle(command)).findFirst();
        return handlerOptional.orElseThrow(() ->
                new IllegalArgumentException("No handler found for " + command.getClass()));
    }

}
