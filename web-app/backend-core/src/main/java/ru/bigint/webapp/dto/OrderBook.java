package ru.bigint.webapp.dto;

import java.util.List;

/**
 * Стакан
 */
public class OrderBook {
    //Заявки
    private List<OrderBookOffer> orderBookOffers;

    public OrderBook(List<OrderBookOffer> orderBookOffers) {
        this.orderBookOffers = orderBookOffers;
    }

    public List<OrderBookOffer> getOffers() {
        return orderBookOffers;
    }

    public void setOffers(List<OrderBookOffer> orderBookOffers) {
        this.orderBookOffers = orderBookOffers;
    }
}
