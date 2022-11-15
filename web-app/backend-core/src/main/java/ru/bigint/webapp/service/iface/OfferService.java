package ru.bigint.webapp.service.iface;

import ru.bigint.webapp.dto.OrderBookOffer;

import java.time.LocalDateTime;
import java.util.List;

public interface OfferService {
    /**
     * Получить все заявки по текущему времени
     */
    List<OrderBookOffer> getActualOffers();

    /**
     * Получить все заявки по указанному времени
     *
     * @param ldt - время для выборки заявок
     */
    List<OrderBookOffer> getOffersByTime(LocalDateTime ldt);
}
