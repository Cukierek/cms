package pl.bottega.cms.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.bottega.cms.model.Show;
import pl.bottega.cms.model.ShowFactory;
import pl.bottega.cms.model.ShowRepository;
import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.CreateShowsCommand;

import java.util.Collection;

@Component
public class CreateShowsHandler implements Handler<CreateShowsCommand>{

	private ShowRepository showRepository;
	private ShowFactory showFactory;

	public CreateShowsHandler(ShowRepository showRepository, ShowFactory showFactory) {
		this.showRepository = showRepository;
		this.showFactory = showFactory;
	}

	@Override
	@Transactional
	public void handle(CreateShowsCommand command) {
		Collection<Show> shows = showFactory.createShows(command);
		shows.stream().forEach(show -> showRepository.save(show));
	}

	@Override
	public Class<? extends Command> getSupportedCommandClass() {
		return CreateShowsCommand.class;
	}
}
