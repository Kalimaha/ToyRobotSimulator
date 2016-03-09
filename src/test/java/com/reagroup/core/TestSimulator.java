package com.reagroup.core;

import com.reagroup.beans.Position;
import com.reagroup.constants.FACING;
import junit.framework.TestCase;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class TestSimulator extends TestCase {

    private Simulator s;

    Position p;

    public void setUp() {
        s = new Simulator();
    }

    public void testDefaultConstructor() {
        assertEquals(5, s.getTabletop().getHeight());
        assertEquals(5, s.getTabletop().getWidth());
        assertEquals(FACING.NORTH, s.getRobot().getFacing());
        assertEquals(0, s.getRobot().getPosition().getX());
        assertEquals(0, s.getRobot().getPosition().getY());
    }

    public void testCleanCommandsList() {
        String[] l1 = new String[]{"move", "left", "move", "place", "report"};
        try {
            l1 = s.cleanCommandsList(l1);
            assertEquals(2, l1.length);
            assertEquals("place", l1[0]);
            assertEquals("report", l1[1]);
        } catch (Exception e) {
            fail();
        }
        String[] l2 = new String[]{"move", "left", "move", "report"};
        try {
            s.cleanCommandsList(l2);
        } catch (Exception e) {
            assertEquals("The commands list must contain at least one PLACE command.", e.getMessage());
        }
    }

    public void testIsValidPosition() {
        p = new Position(10, 0);
        assertFalse(s.isValidPosition(p));
        p = new Position(-10, 0);
        assertFalse(s.isValidPosition(p));
        p = new Position(0, 10);
        assertFalse(s.isValidPosition(p));
        p = new Position(0, -10);
        assertFalse(s.isValidPosition(p));
    }

    public void testSimulate() {
        String[] l1 = new String[]{"place 0,0,north", "move", "report"};
        try {
            s.simulate(l1);
            assertEquals(0, s.getRobot().getPosition().getX());
            assertEquals(1, s.getRobot().getPosition().getY());
            assertEquals(FACING.NORTH, s.getRobot().getFacing());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] l2 = new String[]{"place 0,0,north", "left", "report"};
        try {
            s.simulate(l2);
            assertEquals(0, s.getRobot().getPosition().getX());
            assertEquals(0, s.getRobot().getPosition().getY());
            assertEquals(FACING.WEST, s.getRobot().getFacing());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] l3 = new String[]{"place 1,2,east", "move", "move", "left", "move", "report"};
        try {
            s.simulate(l3);
            assertEquals(3, s.getRobot().getPosition().getX());
            assertEquals(3, s.getRobot().getPosition().getY());
            assertEquals(FACING.NORTH, s.getRobot().getFacing());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] l4 = new String[]{"place 5,5,east"};
        try {
            s.simulate(l4);
        } catch (Exception e) {
            assertEquals("The robot is OUT of the table! The simulation ends.", e.getMessage());
        }
        String[] l5 = new String[]{"place 0,0,east", "move", "move", "move", "move", "move"};
        try {
            s.simulate(l5);
        } catch (Exception e) {
            assertEquals("The robot is OUT of the table! The simulation ends.", e.getMessage());
        }
    }

}