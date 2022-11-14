package ru.bigint.webapp.service.impl;

import org.springframework.stereotype.Service;
import ru.bigint.webapp.dto.Direction;
import ru.bigint.webapp.dto.Offer;
import ru.bigint.webapp.dto.Tiker;
import ru.bigint.webapp.service.iface.OrderService;
import ru.bigint.webapp.utils.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    int ordersCount = 100;
    private Double lastPrice = 65.0000;

    @Override
    public List<Offer> getLastOrders() {
        return getOrdersByTime(LocalDateTime.now());
    }

    @Override
    public List<Offer> getOrdersByTime(LocalDateTime ldt) {
        //На основе текущего курса генерируем синтетические данные
        List<Offer> list = new ArrayList<>();

        Tiker tiker = new Tiker("USDRUB_TOM - USD/РУБ",
                "USDRUB_TOM",
                "USD000UTSTOM",
                1d,
                1000);

        //Формируем заявки на покупку и продажу
        for (int i = 0; i < ordersCount; i++) {
            list.add(makeOrder(tiker, lastPrice, Direction.BUY));
            list.add(makeOrder(tiker, lastPrice, Direction.SELL));
        }

        return list;
    }

    private Offer makeOrder(Tiker tiker, Double lastPrice, Direction direction) {
        //Коэфициент определяющий направление отклонения от цены
        int koef = direction == Direction.BUY ? -1 : 1;

        //Отклонение в процентах от цены lastPrice
        Double rndPercent = 100 + Util.getRandomNumber(1, 50) * 0.01 * koef;
        BigDecimal price = BigDecimal.valueOf(lastPrice * rndPercent / 100).setScale(4, RoundingMode.CEILING);

        return new Offer(tiker, price, Util.getRandomNumber(1, 100), direction);
    }
}
