package com.reagroup.commands;

import com.reagroup.beans.Position;
import com.reagroup.beans.Robot;
import com.reagroup.constants.FACING;
import junit.framework.TestCase;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class TestMove extends TestCase {

    private Command c;

    Robot r;

    public void setUp() {
        c = new Move("move");
    }

    public void testMove() {
        r = new Robot(new Position(0, 0), FACING.NORTH);
        try {
            c.execute(r);
            assertEquals(1, r.getPosition().getY());
        } catch (Exception e) {
            fail();
        }
        r = new Robot(new Position(0, 0), FACING.SOUTH);
        try {
            c.execute(r);
            assertEquals(-1, r.getPosition().getY());
        } catch (Exception e) {
            fail();
        }
        r = new Robot(new Position(0, 0), FACING.EAST);
        try {
            c.execute(r);
            assertEquals(1, r.getPosition().getX());
        } catch (Exception e) {
            fail();
        }
        r = new Robot(new Position(0, 0), FACING.WEST);
        try {
            c.execute(r);
            assertEquals(-1, r.getPosition().getX());
        } catch (Exception e) {
            fail();
        }
    }

}