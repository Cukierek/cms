package pl.bottega.cms.model;


import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

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
}
