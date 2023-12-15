package com.svs.exchange;

import com.svs.exchange.model.Bond;
import com.svs.exchange.model.Sector;
import com.svs.exchange.model.Trader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TradingServiceImpTest {

    private TradingServiceImp service;
    @Mock
    private Trader trader;
    @Mock
    private Sector sector;
    @Mock
    private Bond bond;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new TradingServiceImp();
    }

    @Test
    public void testSomeTradingOperation() {
        TradeRequest mockRequest = new TradeRequest(trader, sector, 2, true, bond, 100);
        when(trader.getLock()).thenReturn(new ReentrantLock());
        when(sector.getLock()).thenReturn(new ReentrantLock());
        assertTrue(service.creditCheck(mockRequest));
    }
}
