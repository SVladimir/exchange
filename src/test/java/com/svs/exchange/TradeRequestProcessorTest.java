package com.svs.exchange;

import com.svs.exchange.model.Bond;
import com.svs.exchange.model.Sector;
import com.svs.exchange.model.Trader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class TradeRequestProcessorTest {

    @Mock
    private TradeRequestQueue queue;

    private TradingService tradingService;
    private TradeRequestProcessor processor;
    @Mock
    private Trader trader;
    @Mock
    private Sector sector;
    @Mock
    private Bond bond;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tradingService = new TradingServiceImp();
        processor = new TradeRequestProcessor(queue, tradingService);
    }

    @Test
    public void testProcessTradeRequest() throws InterruptedException {
        TradeRequest mockRequest = new TradeRequest(trader, sector, 2, true, bond, 100);
        when(queue.take()).thenReturn(mockRequest);
        processor.startProcessing();
        verify(queue, times(4)).take();
    }
}

