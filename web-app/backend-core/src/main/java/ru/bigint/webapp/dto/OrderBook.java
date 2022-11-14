package ru.bigint.webapp.dto;

import java.util.List;

/**
 * Стакан
 */
public class OrderBook {
    //Заявки
    private List<OrderBookItem> offers;

    public OrderBook(List<OrderBookItem> offers) {
        this.offers = offers;
    }

    public List<OrderBookItem> getOffers() {
        return offers;
    }

    public void setOffers(List<OrderBookItem> offers) {
        this.offers = offers;
    }
}
