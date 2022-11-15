package ru.bigint.webapp.dto.chart;

import java.time.LocalDateTime;

/**
 * Свеча графика
 */
public class Candle {

    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Double value;
    private Double volume;
    private LocalDateTime begin;
    private LocalDateTime end;

    public Candle() {
    }

    public Candle(Double open, Double close, Double high, Double low, Double value, Double volume, LocalDateTime begin, LocalDateTime end) {
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.value = value;
        this.volume = volume;
        this.begin = begin;
        this.end = end;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
