package com.solstice.labtwostarter.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="Quote")
public class Quote {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="Symbol")
    private String symbol;

    @Column(name="Price")
    private String price;

    @Column(name="Volume")
    private int volume;

    @Column(name="Date")
    private Timestamp date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
