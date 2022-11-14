package ru.bigint.webapp.dto;

import java.math.BigDecimal;

/**
 * Заявки от участников ФР
 */
public class Offer {
    //Тикер
    private Tiker tiker;
    //Цена
    private BigDecimal price;
    //Число лотов
    private Integer lot;
    //Направление заявки - покупка или продажа
    private Direction direction;

    public Offer() {
    }

    public Offer(Tiker tiker, BigDecimal price, Integer lot, Direction direction) {
        this.tiker = tiker;
        this.price = price;
        this.lot = lot;
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

    public Integer getLot() {
        return lot;
    }

    public void setLot(Integer lot) {
        this.lot = lot;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
