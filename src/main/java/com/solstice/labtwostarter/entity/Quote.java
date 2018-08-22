package com.solstice.labtwostarter.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="Quotes")
public class Quote {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="symbol")
    private String symbol;

    @Column(name="price")
    private Double price;

    @Column(name="volume")
    private int volume;

    @Column(name="date")
    private Timestamp date;

    public Quote(String symbol, Double price, int volume, Timestamp date) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
