package com.svs.exchange;

import com.svs.exchange.model.Bond;
import com.svs.exchange.model.Exchange;
import com.svs.exchange.model.Sector;
import com.svs.exchange.model.Trader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TradeRequestGeneratorTest {

    @Mock
    private TradeRequestQueue queue;
    @Mock
    private Exchange exchange;
    private TradeRequestGenerator generator;

    @Before
    public void setUp() {
        generator = new TradeRequestGenerator(queue, exchange);
    }

    @Test
    public void testRunGeneratesTradeRequest() throws InterruptedException {
        when(exchange.getRandomBond()).thenReturn(new Bond("testBond",100));
        when(exchange.getRandomSector()).thenReturn(new Sector("testSector",1000));
        when(exchange.getRandomTrader()).thenReturn(new Trader("test",1000));

        Thread thread = new Thread(generator);
        thread.start();

        // Sleep to allow the generator to run and generate requests
        Thread.sleep(100);

        // Verify that a trade request was added to the queue
        verify(queue, atLeastOnce()).addTradeRequest(any(TradeRequest.class));

        thread.interrupt(); // Stop the generator thread
    }
}
