package ru.bigint.webapp.dto;

import java.util.List;

/**
 * Стакан
 */
public class OrderBook {
    //Заявки
    private List<Offer> offers;

    public OrderBook(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
