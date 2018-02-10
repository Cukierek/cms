package pl.bottega.cms.model;


import pl.bottega.cms.model.commands.CommandInvalidException;
import pl.bottega.cms.model.commands.SetTicketPricesCommand;
import pl.bottega.cms.model.commands.ValidationErrors;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Embeddable
@Table(name = "ticket_prices")
public class TicketPrices {


    @Transient
    private SetTicketPricesCommand setTicketPricesCommand;
    @Transient
    private ValidationErrors errors;

    @ElementCollection
    private Map<String, BigDecimal> prices;


    public TicketPrices(SetTicketPricesCommand setTicketPricesCommand, ValidationErrors errors) {
        this.setTicketPricesCommand = setTicketPricesCommand;
        this.errors = errors;
        this.prices = setTicketPricesCommand.getPrices();
    }

    public TicketPrices() {
    }

    public Map<String, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }


//    public validatePrices() {
//
//
//
//
//        boolean commandInvalid = false;
//
//        if (prices.containsKey("")) {
//            errors.add("regular price", "Price kind must be defined");
//            commandInvalid = true;
//        }
//
//        if (!(prices.containsKey("regular"))) {
//            validationErrors.add("regular price", "Regular price must be defined");
//            commandInvalid = true;
//        }
//        if (!(prices.containsKey("student"))) {
//            validationErrors.add("student price", "Student price must be defined");
//            commandInvalid = true;
//        }
//
//        if (commandInvalid)
//            throw new CommandInvalidException(validationErrors);
//        return null;
//
//    }
//


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketPrices that = (TicketPrices) o;
        return Objects.equals(prices, that.prices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prices);
    }

    public void validatePrices() {
        validateObligatoryKinds();


        if (errors.any())
                throw new CommandInvalidException(errors);
    }

    private void validateObligatoryKinds() {
        if (!(prices.containsKey("regular") && prices.containsKey("student")))
            errors.add("prices", "Regular and studnet prices must be defined");
    }
}