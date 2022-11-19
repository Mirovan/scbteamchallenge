package ru.bigint.webapp.service.iface;

import ru.bigint.webapp.dto.terminal.OrderBookOffer;

import java.time.LocalDateTime;
import java.util.List;

public interface OfferService {
    /**
     * Получить все заявки по текущему времени
     * @param tikerName - инструмент
     */
    List<OrderBookOffer> getActualOffers(String tikerName);

    /**
     * Получить все заявки по указанному времени
     * @param ldt - время для выборки заявок
     * @param tikerName - инструмент
     */
    List<OrderBookOffer> getOffersByTime(String tikerName, LocalDateTime ldt);
}
