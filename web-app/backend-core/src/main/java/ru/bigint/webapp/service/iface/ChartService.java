package ru.bigint.webapp.service.iface;

import ru.bigint.webapp.dto.chart.Candle;

import java.util.List;

public interface ChartService {
    /**
     * Получить график
     *
     * @param tiker
     * @param timeframe
     */
    List<Candle> getChart(String tiker, Integer timeframe);
}
