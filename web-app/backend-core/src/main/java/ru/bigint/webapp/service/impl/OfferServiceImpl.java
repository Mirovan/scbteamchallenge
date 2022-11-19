package ru.bigint.webapp.service.impl;

import org.springframework.stereotype.Service;
import ru.bigint.webapp.dto.terminal.Operation;
import ru.bigint.webapp.dto.terminal.OrderBookOffer;
import ru.bigint.webapp.dto.terminal.Tiker;
import ru.bigint.webapp.service.iface.ChartService;
import ru.bigint.webapp.service.iface.OfferService;
import ru.bigint.webapp.utils.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OfferServiceImpl implements OfferService {

    //Глубина стакана для генерации синтетических данных
    private int ordersCount = 100;

    private final ChartService chartService;

    public OfferServiceImpl(ChartService chartService) {
        this.chartService = chartService;
    }

    @Override
    public List<OrderBookOffer> getActualOffers(String tikerName) {
        return getOffersByTime(tikerName, LocalDateTime.now());
    }

    @Override
    public List<OrderBookOffer> getOffersByTime(String tikerName, LocalDateTime ldt) {
        //На основе текущего курса генерируем синтетические данные
        List<OrderBookOffer> list = new ArrayList<>();

        //ToDo: сделать для всех инструментов
//        Tiker tiker = new Tiker("USDRUB_TOM - USD/РУБ",
//                "USDRUB_TOM",
//                "USD000UTSTOM",
//                1d,
//                1000);
        Tiker tiker = new Tiker(tikerName,
                tikerName,
                tikerName,
                1d,
                1000);

        Double lastPrice = chartService.getLastPrice();

        //Формируем заявки на покупку и продажу
        for (int i = 0; i < ordersCount; i++) {
            if (Objects.nonNull(lastPrice)) {
                list.add(makeOffer(tiker, lastPrice, Operation.BUY));
                list.add(makeOffer(tiker, lastPrice, Operation.SELL));
            }
        }

        return list;
    }

    private OrderBookOffer makeOffer(Tiker tiker, Double lastPrice, Operation operation) {
        //Коэфициент определяющий направление отклонения от цены
        int koef = operation == Operation.BUY ? -1 : 1;

        //Отклонение в процентах от цены lastPrice
        Double rndPercent = 100 + koef * Util.getRandomNumber(1, 5000) / 10000d;
        BigDecimal price = BigDecimal.valueOf(lastPrice * rndPercent / 100).setScale(3, RoundingMode.CEILING);

        return new OrderBookOffer(tiker, price, Util.getRandomNumber(1, 100), operation);
    }
}
