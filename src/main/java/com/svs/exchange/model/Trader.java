package com.svs.exchange.model;

import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

public class Trader {
    @Getter
    private final String name;
    @Getter
    private final ReentrantLock lock;
    @Getter
    private double portfolioValue;

    public Trader(String name, double portfolioValue) {
        this.name = name;
        this.portfolioValue = portfolioValue;
        this.lock = new ReentrantLock();
    }
    public void increasePortfolioValue(double newPortfolioValue) {
        this.portfolioValue += newPortfolioValue;
        //Not necessary to save low PortfolioValue because it is not clearly for tests results
        portfolioValue=portfolioValue<0?0:portfolioValue;
    }
}

