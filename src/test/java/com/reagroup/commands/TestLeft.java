package com.reagroup.commands;

import com.reagroup.beans.Position;
import com.reagroup.beans.Robot;
import com.reagroup.constants.FACING;
import junit.framework.TestCase;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class TestLeft extends TestCase {

    private Command c;

    Robot r;

    public void setUp() {
        c = new Left("left");
    }

    public void testLeft() {
        r = new Robot(new Position(0, 0), FACING.NORTH);
        try {
            c.execute(r);
            assertEquals(FACING.WEST, r.getFacing());
        } catch (Exception e) {
            fail();
        }
        r = new Robot(new Position(0, 0), FACING.SOUTH);
        try {
            c.execute(r);
            assertEquals(FACING.EAST, r.getFacing());
        } catch (Exception e) {
            fail();
        }
        r = new Robot(new Position(0, 0), FACING.EAST);
        try {
            c.execute(r);
            assertEquals(FACING.NORTH, r.getFacing());
        } catch (Exception e) {
            fail();
        }
        r = new Robot(new Position(0, 0), FACING.WEST);
        try {
            c.execute(r);
            assertEquals(FACING.SOUTH, r.getFacing());
        } catch (Exception e) {
            fail();
        }
    }

}