package com.svs.exchange;

import com.svs.exchange.model.Bond;
import com.svs.exchange.model.Sector;
import com.svs.exchange.model.Trader;
import lombok.Getter;

public class TradeRequest implements Comparable<TradeRequest> {
    @Getter
    private final Trader trader;
    @Getter
    private final Sector sector;
    @Getter
    private final Bond bond;
    private final boolean isBuy;
    private final double priority;
    @Getter
    private  final double count;

    public TradeRequest(Trader trader, Sector sector, double count, boolean isBuy, Bond bond, double priority) {
        this.trader = trader;
        this.sector = sector;
        this.count = count;
        this.isBuy = isBuy;
        this.bond = bond;
        this.priority = priority;
    }


    @Override
    public int compareTo(TradeRequest other) {
        if (!this.bond.equals(other.bond)) {
            return 0;
        }
        return Double.compare(other.priority, this.priority);
    }

    public boolean isBuy() {
        return isBuy;
    }
    @Override
    public String toString() {
        return "TradeRequest{" +
                "trader=" + trader.getName() +
                ", sector=" + sector.getName() +
                ", count=" + count +
                ", isBuy=" + isBuy +
                ", bond=" + bond.getName() +
                ", priority=" + priority +
                '}';
    }

}

