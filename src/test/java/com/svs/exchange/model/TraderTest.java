package com.svs.exchange.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TraderTest {

    private Trader trader;

    @BeforeEach
    public void setUp() {
        trader = new Trader("John Doe", 1000.0);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("John Doe", trader.getName());
        assertEquals(1000.0, trader.getPortfolioValue(), 0.001);
    }

    @Test
    public void testIncreasePortfolioValue() {
        trader.increasePortfolioValue(500.0);
        assertEquals(1500.0, trader.getPortfolioValue(), 0.001);

        // Testing decrease
        trader.increasePortfolioValue(-600.0);
        assertEquals(900.0, trader.getPortfolioValue(), 0.001);

        // Test to ensure portfolioValue doesn't drop below 0
        trader.increasePortfolioValue(-1000.0);
        assertEquals(0.0, trader.getPortfolioValue(), 0.001);
    }

    @Test
    public void testLockIsNotNull() {
        assertNotNull(trader.getLock());
    }
}
