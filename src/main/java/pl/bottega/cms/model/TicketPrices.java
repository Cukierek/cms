package pl.bottega.cms.model;


import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Map;

@Embeddable
@Table(name = "ticket_prices")
public class TicketPrices {

    @ElementCollection
    private Map<String, BigDecimal> prices;


    public TicketPrices() {
    }

    public TicketPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }

    public Map<String, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }
}
