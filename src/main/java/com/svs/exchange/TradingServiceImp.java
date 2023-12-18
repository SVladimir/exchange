package com.svs.exchange;

import com.svs.exchange.model.Bond;
import com.svs.exchange.model.Sector;
import com.svs.exchange.model.Trader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TradingServiceImp implements TradingService {
    private static final Logger logger = LoggerFactory.getLogger(TradingServiceImp.class);
    private static final String BUY = "buy";
    private static final String SELL = "sell";
    private final int signMinus=-1;

    @Override
    public boolean creditCheck(TradeRequest tradeRequest) {
        Bond bond = tradeRequest.getBond();
        Trader trader = tradeRequest.getTrader();
        Sector sector = tradeRequest.getSector();
        double count = tradeRequest.getCount();
        double cost = bond.getPrice() * count;
        boolean result = true;
        String typeOperation = BUY;
        if (tradeRequest.isBuy()) {
            typeOperation = SELL;
            cost = signMinus * cost;
        }
        trader.getLock().lock();
        sector.getLock().lock();
        try {
            if (sector.getTotalInvestment() + cost <= sector.getLimit()) {
                trader.increasePortfolioValue(cost);
                sector.increaseInvestment(cost);
                // Implement the actual purchase logic
                logger.info("Trader {} and operation {} for {} of {} in sector {} was finished", trader.getName(), typeOperation, count, bond.getName(), sector.getName());
                System.out.println(sector.getTotalInvestment() + cost + "<= " + sector.getLimit());
            } else {
                result = false;
                logger.error("Transaction failed for trader {} with portfolio={} and sectorLimit={} is exceeded {}", trader.getName(), trader.getPortfolioValue(), sector.getLimit(), sector.getTotalInvestment() + cost);
            }
        } finally {
            sector.getLock().unlock();
            trader.getLock().unlock();
        }
        return result;
    }

}


