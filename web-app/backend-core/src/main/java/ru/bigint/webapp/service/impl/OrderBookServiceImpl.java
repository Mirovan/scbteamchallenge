package ru.bigint.webapp.service.impl;

import org.springframework.stereotype.Service;
import ru.bigint.webapp.dto.Direction;
import ru.bigint.webapp.dto.Offer;
import ru.bigint.webapp.dto.OrderBook;
import ru.bigint.webapp.dto.OrderBookItem;
import ru.bigint.webapp.service.iface.OrderBookService;
import ru.bigint.webapp.service.iface.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderBookServiceImpl implements OrderBookService {

    private int orderBookDepth = 20;

    private final OrderService orderService;

    public OrderBookServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public OrderBook getOrderBook(String tiker) {
        List<Offer> offers = orderService.getLastOrders();

        return new OrderBook(createOrderBookItems(offers));
    }

    private List<OrderBookItem> createOrderBookItems(List<Offer> list) {
        //объединяем по ценам офферы
        Map<BigDecimal, List<Offer>> map = list.stream()
                .collect(Collectors.groupingBy(Offer::getPrice));

        List<OrderBookItem> offers = map.entrySet().stream()
                .map(item -> {
                    BigDecimal price = item.getKey();
                    Integer lot = item.getValue().stream()
                            .map(Offer::getLot)
                            .mapToInt(Integer::intValue).sum();
                    Direction dir = item.getValue().stream().findAny().get().getDirection();
                    return new OrderBookItem(price, lot, dir);
                })
                .toList();

        List<OrderBookItem> asks = offers.stream()
                .filter(item -> item.getDirection() == Direction.SELL)
                .sorted(Comparator.comparing(OrderBookItem::getPrice).reversed())
                .toList();
        asks = asks.subList(asks.size() - orderBookDepth, asks.size());

        List<OrderBookItem> bids = offers.stream()
                .filter(item -> item.getDirection() == Direction.BUY)
                .sorted(Comparator.comparing(OrderBookItem::getPrice).reversed())
                .toList();
        bids = bids.subList(0, orderBookDepth);

        List<OrderBookItem> res = new ArrayList<>();
        res.addAll(asks);
        res.addAll(bids);

        return res;
    }

}
