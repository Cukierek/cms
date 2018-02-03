package pl.bottega.cms.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.bottega.cms.model.Show;
import pl.bottega.cms.model.ShowFactory;
import pl.bottega.cms.model.ShowRepository;
import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.CreateShowsCommand;
import pl.bottega.cms.model.commands.ValidationErrors;

import java.util.Collection;

@Component
public class CreateShowsHandler implements Handler<CreateShowsCommand, Void> {

	private ShowRepository showRepository;
	private ShowFactory showFactory;

	public CreateShowsHandler(ShowRepository showRepository, ShowFactory showFactory) {
		this.showRepository = showRepository;
		this.showFactory = showFactory;
	}

	@Override
	@Transactional
	public Void handle(CreateShowsCommand command) {
		ValidationErrors errors = showFactory.validate(command);
		if (!errors.any()) {
			Collection<Show> shows = showFactory.createShows(command);
			shows.stream().forEach(show -> showRepository.save(show));
		}
		return  null;
	}

	@Override
	public Class<? extends Command> getSupportedCommandClass() {
		return CreateShowsCommand.class;
	}
}
