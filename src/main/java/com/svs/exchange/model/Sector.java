package com.svs.exchange.model;

import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

public class Sector {
    @Getter
    private final String name;
    @Getter
    private final double limit;
    @Getter
    private double totalInvestment;
    @Getter
    private final ReentrantLock lock;

    public Sector(String name, double limit) {
        this.name = name;
        this.limit = limit;
        this.totalInvestment = 0;
        this.lock = new ReentrantLock();
    }
        public void increaseInvestment(double amount) {
        totalInvestment += amount;
        //Not necessary to save low investments because it is not clearly for tests results
        totalInvestment=totalInvestment<0?0:totalInvestment;
    }
}
