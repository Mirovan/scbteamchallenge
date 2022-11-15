package ru.bigint.webapp.service.iface;

import ru.bigint.webapp.dto.Offer;

import java.time.LocalDateTime;
import java.util.List;

public interface OfferService {
    /**
     * Получить все заявки по текущему времени
     * */
    List<Offer> getLastOrders();

    /**
     * Получить все заявки по указанному времени
     *
     * @param ldt - время для выборки заявок
     * */
    List<Offer> getOrdersByTime(LocalDateTime ldt);
}
