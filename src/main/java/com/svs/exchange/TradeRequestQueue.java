package com.svs.exchange;

import org.springframework.stereotype.Component;
import java.util.concurrent.PriorityBlockingQueue;

@Component
public class TradeRequestQueue {
    private final PriorityBlockingQueue<TradeRequest> queue;

    public TradeRequestQueue() {
        this.queue = new PriorityBlockingQueue<>();
    }

    public void addTradeRequest(TradeRequest request) {
        queue.put(request);
    }

    public TradeRequest take() throws InterruptedException {
        return queue.take();
    }
}
