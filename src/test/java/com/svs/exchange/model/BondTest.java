package com.svs.exchange.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BondTest {

    private Bond bond;

    @Before
    public void setUp() {
        bond = new Bond("Bond Name",100);
    }

    @Test
    public void testGetAndSetProperties() {
        assertEquals("Bond Name", bond.getName());
        assertEquals(100, bond.getPrice(),0.001);
        // Test other properties similarly
    }
}
