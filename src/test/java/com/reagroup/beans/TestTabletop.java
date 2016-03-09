package com.reagroup.beans;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class TestTabletop extends TestCase {

    private Tabletop t;

    public void setUp() {
        t = new Tabletop();
    }

    public void testDefaultConstructor() {
        assertEquals(5, t.getWidth());
        assertEquals(5, t.getHeight());
    }

    public void testConstructor() {
        t = new Tabletop(7, 9);
        assertEquals(7, t.getWidth());
        assertEquals(9, t.getHeight());
    }

}