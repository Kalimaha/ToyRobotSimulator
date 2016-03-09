package com.reagroup.commands;

import com.reagroup.beans.Robot;
import com.reagroup.constants.FACING;
import junit.framework.TestCase;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class TestPlace extends TestCase {

    private Command c;

    private Robot r;

    public void setUp() {
        r = new Robot();
    }

    public void testConstructor() {
        c = new Place("place 0,0,north");
        assertEquals("PLACE 0,0,NORTH", c.getCommandString());
    }

    public void testExecute() {
        c = new Place("place 1,2,WEST");
        try {
            c.execute(r);
            assertEquals(1, r.getPosition().getX());
            assertEquals(2, r.getPosition().getY());
            assertEquals(FACING.WEST, r.getFacing());
        } catch (Exception e) {
            assertEquals("Please provide a valid facing direction.", e.getMessage());
        }
    }

    public void testParseParameters() {
        c = new Place("place 0,north");
        try {
            c.execute(r);
        } catch (Exception e) {
            assertEquals("The PLACE command requires 3 parameters.", e.getMessage());
        }
    }

    public void testParseX() {
        c = new Place("place a,0,north");
        try {
            c.execute(r);
        } catch (Exception e) {
            assertEquals("Please provide a valid number for the x coordinate.", e.getMessage());
        }
    }

    public void testParseY() {
        c = new Place("place 0,z,north");
        try {
            c.execute(r);
        } catch (Exception e) {
            assertEquals("Please provide a valid number for the y coordinate.", e.getMessage());
        }
    }

    public void testParseFacing() {
        c = new Place("place 0,0,right");
        try {
            c.execute(r);
        } catch (Exception e) {
            assertEquals("Please provide a valid facing direction.", e.getMessage());
        }
    }

}