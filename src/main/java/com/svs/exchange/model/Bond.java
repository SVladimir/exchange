package com.svs.exchange.model;


import lombok.Getter;

public class Bond {
    @Getter
    private final String name;
    @Getter
    private final double price;

    public Bond(String name, double price) {
        this.name = name;
        this.price = price;
    }


}