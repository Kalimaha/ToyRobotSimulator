package com.reagroup.beans;

import com.reagroup.constants.FACING;
import junit.framework.TestCase;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class TestRobot extends TestCase {

    private Robot r;

    public void setUp() {
        r = new Robot();
    }

    public void testDefaultConstructor() {
        assertEquals(0, r.getPosition().getX());
        assertEquals(0, r.getPosition().getY());
        assertEquals(FACING.NORTH, r.getFacing());
    }

    public void testConstructor() {
        r = new Robot(new Position(1, 2), FACING.WEST);
        assertEquals(1, r.getPosition().getX());
        assertEquals(2, r.getPosition().getY());
        assertEquals(FACING.WEST, r.getFacing());
    }

}