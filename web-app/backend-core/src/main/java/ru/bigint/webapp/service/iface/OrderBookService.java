package ru.bigint.webapp.service.iface;

import ru.bigint.webapp.dto.OrderBook;

public interface OrderBookService {
    /**
     * Получить стакан по заявкам
     */
    OrderBook getOrderBook(String tiker);
}
