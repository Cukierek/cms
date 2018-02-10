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
    	validateMovieId(errors);
    	validatePrices(errors);
    }

	private void validatePrices(ValidationErrors errors) {
		validatePresence(errors, "movieId", movieId);
		if (movieId < 1) errors.add("movieId", "Can't be less than 1");
	}

	private void validateMovieId(ValidationErrors errors) {
		validatePresence(errors, "prices", prices);
		if (prices != null) {
			prices.forEach((priceKey, priceValue)-> {
				if (priceKey == null) {
					errors.add("priceKey", "Can't be empty");
				} else {
					if(priceKey.isEmpty()) errors.add("priceKey", "Can't be empty");
				}
				if (priceValue == null) {
					errors.add("priceValue", "Can't be empty");
				} else {
					if (priceValue.intValue() < 0) errors.add("priceValue", "Can't be less than 0");
				}
			});
		}
	}
}
