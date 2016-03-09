package com.reagroup.beans;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class TestPosition extends TestCase {

    private Position p;

    public void setUp() {
        p = new Position(0, 0);
    }

    public void testConstructor() {
        assertEquals(0, p.getX());
        assertEquals(0, p.getY());
    }

    public void testX() {
        assertEquals(0, p.getX());
        p.setX(10);
        assertEquals(10, p.getX());
    }

    public void testY() {
        assertEquals(0, p.getY());
        p.setY(10);
        assertEquals(10, p.getY());
    }

}