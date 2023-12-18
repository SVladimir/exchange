package com.svs.exchange;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TradeRequestProcessor {
    private final TradeRequestQueue requestQueue;
    private final ExecutorService executorService;
    private final TradingService tradingService;
    private final int maxThreads=8;
    private final int poolSize=4;

    public TradeRequestProcessor(TradeRequestQueue requestQueue, TradingService tradingService) {
        this.requestQueue = requestQueue;
        this.executorService = Executors.newFixedThreadPool(poolSize);
        this.tradingService = tradingService;

    }

    public void startProcessing() {
        for (int i = 0; i < maxThreads; i++) {
            executorService.submit(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        TradeRequest request = requestQueue.take();
                        processRequest(request);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    private void processRequest(TradeRequest request) {
        //checking trading limits
        tradingService.creditCheck(request);
    }

    public void stopProcessing() {
        executorService.shutdownNow(); // Stop threads
    }

    @PreDestroy
    public void cleanUp() {
        stopProcessing();
    }
}

