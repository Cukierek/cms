package pl.bottega.cms.model;

import javax.persistence.Embeddable;

@Embeddable
public enum ReservationStatus {

    PENDING,
    PAID,
    CANCELED,
    PAYMENT_FAILED

}
