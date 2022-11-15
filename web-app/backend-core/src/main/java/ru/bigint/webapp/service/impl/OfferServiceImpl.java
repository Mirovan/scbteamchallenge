package ru.bigint.webapp.service.impl;

import org.springframework.stereotype.Service;
import ru.bigint.webapp.dto.Direction;
import ru.bigint.webapp.dto.OrderBookOffer;
import ru.bigint.webapp.dto.Tiker;
import ru.bigint.webapp.service.iface.OfferService;
import ru.bigint.webapp.utils.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    int ordersCount = 100;
    private Double lastPrice = 65.0000;

    @Override
    public List<OrderBookOffer> getActualOffers() {
        return getOffersByTime(LocalDateTime.now());
    }

    @Override
    public List<OrderBookOffer> getOffersByTime(LocalDateTime ldt) {
        //На основе текущего курса генерируем синтетические данные
        List<OrderBookOffer> list = new ArrayList<>();

        Tiker tiker = new Tiker("USDRUB_TOM - USD/РУБ",
                "USDRUB_TOM",
                "USD000UTSTOM",
                1d,
                1000);

        //Формируем заявки на покупку и продажу
        for (int i = 0; i < ordersCount; i++) {
            list.add(makeOffer(tiker, lastPrice, Direction.BUY));
            list.add(makeOffer(tiker, lastPrice, Direction.SELL));
        }

        return list;
    }

    private OrderBookOffer makeOffer(Tiker tiker, Double lastPrice, Direction direction) {
        //Коэфициент определяющий направление отклонения от цены
        int koef = direction == Direction.BUY ? -1 : 1;

        //Отклонение в процентах от цены lastPrice
        Double rndPercent = 100 + koef * Util.getRandomNumber(1, 5000) / 10000d;
        BigDecimal price = BigDecimal.valueOf(lastPrice * rndPercent / 100).setScale(3, RoundingMode.CEILING);

        return new OrderBookOffer(tiker, price, Util.getRandomNumber(1, 100), direction);
    }
}
