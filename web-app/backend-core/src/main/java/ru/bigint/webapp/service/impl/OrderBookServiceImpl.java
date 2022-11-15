package ru.bigint.webapp.service.impl;

import org.springframework.stereotype.Service;
import ru.bigint.webapp.dto.Direction;
import ru.bigint.webapp.dto.OrderBookOffer;
import ru.bigint.webapp.dto.OrderBook;
import ru.bigint.webapp.service.iface.OrderBookService;
import ru.bigint.webapp.service.iface.OfferService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderBookServiceImpl implements OrderBookService {

    private int orderBookDepth = 20;

    private final OfferService offerService;

    public OrderBookServiceImpl(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public OrderBook getOrderBook(String tiker) {
        List<OrderBookOffer> orderBookOffers = offerService.getActualOffers();

        return new OrderBook(createOrderBookItems(orderBookOffers));
    }

    private List<OrderBookOffer> createOrderBookItems(List<OrderBookOffer> list) {
        //объединяем по ценам офферы
        Map<BigDecimal, List<OrderBookOffer>> map = list.stream()
                .collect(Collectors.groupingBy(OrderBookOffer::getPrice));

        List<OrderBookOffer> orderBookOffers = map.entrySet().stream()
                .map(item -> {
                    BigDecimal price = item.getKey();
                    Integer lot = item.getValue().stream()
                            .map(OrderBookOffer::getVolume)
                            .mapToInt(Integer::intValue).sum();
                    Direction dir = item.getValue().stream().findAny().get().getDirection();
                    return new OrderBookOffer(price, lot, dir);
                })
                .toList();

        List<OrderBookOffer> asks = orderBookOffers.stream()
                .filter(item -> item.getDirection() == Direction.SELL)
                .sorted(Comparator.comparing(OrderBookOffer::getPrice).reversed())
                .toList();
        asks = asks.subList(asks.size() - orderBookDepth, asks.size());

        List<OrderBookOffer> bids = orderBookOffers.stream()
                .filter(item -> item.getDirection() == Direction.BUY)
                .sorted(Comparator.comparing(OrderBookOffer::getPrice).reversed())
                .toList();
        bids = bids.subList(0, orderBookDepth);

        List<OrderBookOffer> res = new ArrayList<>();
        res.addAll(asks);
        res.addAll(bids);

        return res;
    }

}
