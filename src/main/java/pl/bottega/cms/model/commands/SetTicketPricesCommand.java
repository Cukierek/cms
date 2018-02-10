package pl.bottega.cms.model.commands;

import java.math.BigDecimal;
import java.util.Map;

public class SetTicketPricesCommand implements Command {

    Integer movieId;
    Map<String, BigDecimal> prices;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Map<String, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }

    @Override
    public void validate(ValidationErrors errors) {
        validatePresence(errors, "movieId", movieId);
        validatePresence(errors, "prices", prices);
    }
}
