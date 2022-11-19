package ru.bigint.webapp.service.iface;

import ru.bigint.webapp.dto.terminal.OrderBook;

public interface OrderBookService {
    /**
     * Получить стакан по заявкам
     */
    OrderBook getOrderBook(String tiker);
}
