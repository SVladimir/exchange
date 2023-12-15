package com.svs.exchange.config;

import com.svs.exchange.TradeRequestProcessor;
import com.svs.exchange.TradeRequestQueue;
import com.svs.exchange.TradingService;
import com.svs.exchange.TradingServiceImp;
import com.svs.exchange.model.Exchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TradeConfig {

    @Bean
    public TradeRequestQueue tradeRequestQueue() {
        return new TradeRequestQueue();
    }

    @Bean
    public TradingService tradingService() {
        return new TradingServiceImp();
    }

    @Bean
    public Exchange exchange() {
        return new Exchange();
    }

    @Bean
    public TradeRequestProcessor tradeRequestProcessor(TradeRequestQueue queue, TradingService tradingService) {
        return new TradeRequestProcessor(queue, tradingService);
    }
}