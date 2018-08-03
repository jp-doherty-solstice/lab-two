package com.solstice.labtwostarter.entity;

import static java.lang.Math.toIntExact;

public class AggregatedStockData {

    private double dailyHigh = 54.94;
    private double dailyLow = 64.32;
    private int totalVolume = 654;

//    public AggregatedStockData(String dailyHigh, String dailyLow, Long totalVolume) {
//        System.out.println(dailyHigh);
//        System.out.println(dailyLow);
//        System.out.println(totalVolume);
//        this.dailyHigh = Double.parseDouble(dailyHigh);
//        this.dailyLow = Double.parseDouble(dailyLow);
//        this.totalVolume = toIntExact(totalVolume);
//    }

    public AggregatedStockData(String dailyHigh) {
        System.out.println(dailyHigh);
    }

    public double getDailyHigh() {
        return dailyHigh;
    }

    public void setDailyHigh(double dailyHigh) {
        this.dailyHigh = dailyHigh;
    }

    public double getDailyLow() {
        return dailyLow;
    }

    public void setDailyLow(double dailyLow) {
        this.dailyLow = dailyLow;
    }

    public int getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }
}
