package pl.bottega.cms.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bottega.cms.api.CommandGateway;
import pl.bottega.cms.domain.commands.CreateMovieCommand;


@RestController
@RequestMapping("/movies")
public class MovieController {

    private CommandGateway commandGateway;

    public MovieController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping("/create")
    public void create(@RequestBody CreateMovieCommand cmd) {
        commandGateway.execute(cmd);
    }



}
