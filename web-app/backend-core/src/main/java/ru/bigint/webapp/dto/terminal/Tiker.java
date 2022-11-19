package ru.bigint.webapp.dto.terminal;

public class Tiker {
    //Полное название
    private String fullname;
    //Краткое название
    private String shortname;
    //Код инструмента
    private String code;
    //Шаг цены
    private Double priceStep;
    //Размер минимального лота, в ед. валюты лота
    private Integer lot;

    public Tiker() {
    }

    public Tiker(String fullname, String shortname, String code, Double priceStep, Integer lot) {
        this.fullname = fullname;
        this.shortname = shortname;
        this.code = code;
        this.priceStep = priceStep;
        this.lot = lot;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPriceStep() {
        return priceStep;
    }

    public void setPriceStep(Double priceStep) {
        this.priceStep = priceStep;
    }

    public Integer getLot() {
        return lot;
    }

    public void setLot(Integer lot) {
        this.lot = lot;
    }
}
