package ru.bigint.webapp.dto;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Элемент стакана.
 * По сути это урезанный класс Offer - сделано для уменьшения размера сообщения между фронтом и беком
 * */
public class OrderBookItem {
    private BigDecimal price;
    private Integer lot;
    private Direction direction;

    public OrderBookItem() {
    }

    public OrderBookItem(BigDecimal price, Integer lot, Direction direction) {
        this.price = price;
        this.lot = lot;
        this.direction = direction;
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
