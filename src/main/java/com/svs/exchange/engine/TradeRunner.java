package com.svs.exchange.engine;

import com.svs.exchange.TradeRequestGenerator;
import com.svs.exchange.TradeRequestProcessor;
import com.svs.exchange.TradeRequestQueue;
import com.svs.exchange.model.Exchange;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TradeRunner implements CommandLineRunner {

    private final TradeRequestProcessor processor;
    private final TradeRequestQueue queue;
    private final Exchange exchange;

    public TradeRunner(TradeRequestProcessor processor, TradeRequestQueue queue, Exchange exchange) {
        this.processor = processor;
        this.queue = queue;
        this.exchange = exchange;
    }

    @Override
    public void run(String... args) throws Exception {
        processor.startProcessing();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            executorService.submit(new TradeRequestGenerator(queue, exchange));
        }
    }
}