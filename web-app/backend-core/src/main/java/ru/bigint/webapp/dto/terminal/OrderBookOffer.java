package ru.bigint.webapp.dto.terminal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.bigint.webapp.utils.PriceSerializer;

import java.math.BigDecimal;

/**
 * Заявки от участников ФР
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderBookOffer {
    //Тикер
    private Tiker tiker;
    //Цена
    @JsonSerialize(using = PriceSerializer.class)
    private BigDecimal price;
    //Число лотов
    private Integer volume;
    //Направление заявки - покупка или продажа
    private Operation operation;

    public OrderBookOffer() {
    }

    public OrderBookOffer(Tiker tiker, BigDecimal price, Integer volume, Operation operation) {
        this.tiker = tiker;
        this.price = price;
        this.volume = volume;
        this.operation = operation;
    }

    public OrderBookOffer(BigDecimal price, Integer volume, Operation operation) {
        this.price = price;
        this.volume = volume;
        this.operation = operation;
    }

    public Tiker getTiker() {
        return tiker;
    }

    public void setTiker(Tiker tiker) {
        this.tiker = tiker;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Operation getDirection() {
        return operation;
    }

    public void setDirection(Operation operation) {
        this.operation = operation;
    }
}
