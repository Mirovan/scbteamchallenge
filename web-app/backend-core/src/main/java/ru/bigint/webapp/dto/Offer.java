package ru.bigint.webapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.bigint.webapp.utils.PriceSerializer;

import java.math.BigDecimal;

/**
 * Заявки от участников ФР
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Offer {
    //Тикер
    private Tiker tiker;
    //Цена
    @JsonSerialize(using = PriceSerializer.class)
    private BigDecimal price;
    //Число лотов
    private Integer volume;
    //Направление заявки - покупка или продажа
    private Direction direction;

    public Offer() {
    }

    public Offer(Tiker tiker, BigDecimal price, Integer volume, Direction direction) {
        this.tiker = tiker;
        this.price = price;
        this.volume = volume;
        this.direction = direction;
    }

    public Offer(BigDecimal price, Integer volume, Direction direction) {
        this.price = price;
        this.volume = volume;
        this.direction = direction;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
