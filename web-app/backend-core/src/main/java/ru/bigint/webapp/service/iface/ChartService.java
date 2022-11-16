package ru.bigint.webapp.service.iface;

import ru.bigint.webapp.dto.chart.Candle;

import java.util.List;

public interface ChartService {
    /**
     * Получить график
     *
     * @param tiker     - инструмент
     * @param timeframe - таймфрейм
     */
    List<Candle> getChart(String tiker, Integer timeframe);

    /**
     * Получить последнюю свечу
     */
    Candle getLastCandle();

    /**
     * Получить последнюю цену по свече
     */
    Double getLastPrice();
}
