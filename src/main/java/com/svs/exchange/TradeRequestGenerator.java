package com.svs.exchange;

import com.svs.exchange.model.Bond;
import com.svs.exchange.model.Exchange;
import com.svs.exchange.model.Sector;
import com.svs.exchange.model.Trader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class TradeRequestGenerator implements Runnable {
    private final TradeRequestQueue queue;
    private final Exchange exchange;
    private static final Logger logger = LoggerFactory.getLogger(TradeRequestGenerator.class);
    private final int randomSizeLimit=10;
    public TradeRequestGenerator(TradeRequestQueue queue, Exchange exchange) {
        this.queue = queue;
        this.exchange = exchange;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            Bond bond = exchange.getRandomBond();
            Sector sector = exchange.getRandomSector();
            Trader trader = exchange.getRandomTrader();
            double portfolio = trader.getPortfolioValue();
            int count = random.nextInt(randomSizeLimit) + 1;
            boolean isBuy = count % 2 == 0;
            TradeRequest tradeRequest=new TradeRequest(trader, sector, count, isBuy, bond, portfolio);
            queue.addTradeRequest(new TradeRequest(trader, sector, count, isBuy, bond, portfolio));
            logger.info("Send TradeRequest={}", tradeRequest);
            try {
                Thread.sleep(5); // Slight delay to avoid excessive processor load
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
