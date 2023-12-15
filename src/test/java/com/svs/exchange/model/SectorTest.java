package com.svs.exchange.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SectorTest {

    private Sector sector;

    @BeforeEach
    public void setUp() {
        sector = new Sector("Technology", 1000.0);
    }

    @Test
    public void testConstructor() {
        assertEquals("Technology", sector.getName());
        assertEquals(1000.0, sector.getLimit(), 0.001);
        assertEquals(0.0, sector.getTotalInvestment(), 0.001);
    }

    @Test
    public void testIncreaseInvestment() {
        sector.increaseInvestment(500.0);
        assertEquals(500.0, sector.getTotalInvestment(), 0.001);

        sector.increaseInvestment(-200.0);
        assertEquals(300.0, sector.getTotalInvestment(), 0.001);

        // Test to ensure totalInvestment doesn't drop below 0
        sector.increaseInvestment(-500.0);
        assertEquals(0.0, sector.getTotalInvestment(), 0.001);
    }

    @Test
    public void testLockIsNotNull() {
        assertNotNull(sector.getLock());
    }
}
