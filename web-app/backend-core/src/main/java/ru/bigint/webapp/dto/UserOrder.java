package ru.bigint.webapp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Заявки клиента
 * */
public class UserOrder {
    private Integer id;
    //Время заявки
    private LocalDateTime dateTime;
    //Краткое название
    private String tiker;
    //Операция
    private String operation;
    //Цена
    private Double price;
    //Число лотов
    private Integer lot;
    //Объем
    private BigDecimal volume;
    //Состояние операции
    private String status;

    public UserOrder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTiker() {
        return tiker;
    }

    public void setTiker(String tiker) {
        this.tiker = tiker;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getLot() {
        return lot;
    }

    public void setLot(Integer lot) {
        this.lot = lot;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
