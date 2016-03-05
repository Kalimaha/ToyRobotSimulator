package com.reagroup.core;

import com.reagroup.beans.Position;
import com.reagroup.constants.FACING;
import junit.framework.TestCase;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class TestSimulator extends TestCase {

    public Simulator s;

    public void setUp() {
        s = new Simulator();
    }

    public void testPosition() {
        assertEquals(0, s.getPosition().getX());
        assertEquals(0, s.getPosition().getY());
        assertEquals(FACING.NORTH, s.getPosition().getFacing());
        assertEquals(5, s.getTable().getWidth());
        assertEquals(5, s.getTable().getHeight());
        assertNotNull(s.getReports());
    }

    public void testExecute() {
        String[] commands_1 = {"PLACE 0,0,NORTH", "MOVE", "REPORT"};
        try {
            s.execute(commands_1);
            assertEquals("X: 0, Y: 1, FACING: NORTH", s.report());
            assertEquals("X: 0, Y: 1, FACING: NORTH", s.getReports().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] commands_2 = {"PLACE 0,0,NORTH", "LEFT", "REPORT"};
        try {
            s.execute(commands_2);
            assertEquals("X: 0, Y: 0, FACING: WEST", s.report());
            assertEquals("X: 0, Y: 0, FACING: WEST", s.getReports().get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] commands_3 = {"PLACE 1,2,EAST", "MOVE", "MOVE", "LEFT", "MOVE", "REPORT"};
        try {
            s.execute(commands_3);
            assertEquals("X: 3, Y: 3, FACING: NORTH", s.report());
            assertEquals("X: 3, Y: 3, FACING: NORTH", s.getReports().get(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* The application should discard all commands in the sequence until a valid PLACE command has been executed. */
    public void testCleanCommands() {
        String[] commands = {"REPORT", "MOVE", "not a command", "PLACE 1,2,NORTH", "MOVE", "REPORT"};
        commands = s.cleanCommands(commands);
        assertEquals(3, commands.length);
        assertEquals("PLACE 1,2,NORTH", commands[0]);
    }

    public void testParsePosition() {
        String command = "PARSE 0,0,NORTH";
        Position p;
        try {
            p = s.parsePosition(command);
            assertEquals(0, p.getX());
            assertEquals(0, p.getY());
            assertEquals(FACING.NORTH, p.getFacing());
        } catch (Exception e) {
            e.printStackTrace();
        }
        command = "PARSE z,0,NORTH";
        try {
            s.parsePosition(command);
        } catch (Exception e) {
            assertEquals("X is not a valid coordinate.", e.getMessage());
        }
        command = "PARSE 0,z,NORTH";
        try {
            s.parsePosition(command);
        } catch (Exception e) {
            assertEquals("Y is not a valid coordinate.", e.getMessage());
        }
        command = "PARSE 0,0,z";
        try {
            s.parsePosition(command);
        } catch (Exception e) {
            assertEquals("FACING is not a valid value.", e.getMessage());
        }
    }

    public void testPlace() {
        Position p = new Position(1, 2, FACING.SOUTH);
        s.place(p);
        assertEquals(1, s.getPosition().getX());
        assertEquals(2, s.getPosition().getY());
        assertEquals(FACING.SOUTH, s.getPosition().getFacing());
    }

    public void testMove() {
        s.move();
        assertEquals(1, s.getPosition().getY());
        s.setPosition(new Position(0, 1, FACING.SOUTH));
        s.move();
        assertEquals(0, s.getPosition().getY());
        s.setPosition(new Position(0, 0, FACING.EAST));
        s.move();
        assertEquals(1, s.getPosition().getX());
        s.setPosition(new Position(1, 0, FACING.WEST));
        s.move();
        assertEquals(0, s.getPosition().getX());
    }

    public void testLeft() {
        s.left();
        assertEquals(FACING.WEST, s.getPosition().getFacing());
        s.setPosition(new Position(0, 0, FACING.SOUTH));
        s.left();
        assertEquals(FACING.EAST, s.getPosition().getFacing());
        s.setPosition(new Position(0, 0, FACING.EAST));
        s.left();
        assertEquals(FACING.NORTH, s.getPosition().getFacing());
        s.setPosition(new Position(0, 0, FACING.WEST));
        s.left();
        assertEquals(FACING.SOUTH, s.getPosition().getFacing());
    }

    public void testRight() {
        s.right();
        assertEquals(FACING.EAST, s.getPosition().getFacing());
        s.setPosition(new Position(0, 0, FACING.SOUTH));
        s.right();
        assertEquals(FACING.WEST, s.getPosition().getFacing());
        s.setPosition(new Position(0, 0, FACING.EAST));
        s.right();
        assertEquals(FACING.SOUTH, s.getPosition().getFacing());
        s.setPosition(new Position(0, 0, FACING.WEST));
        s.right();
        assertEquals(FACING.NORTH, s.getPosition().getFacing());
    }

    public void testReport() {
        assertEquals("X: 0, Y: 0, FACING: NORTH", s.report());
    }

    public void testIsValidPosition() {
        Position p = new Position(0, 0, FACING.NORTH);
        assertTrue(s.isValidPosition(p));
        p = new Position(-3, 0, FACING.NORTH);
        assertFalse(s.isValidPosition(p));
        p = new Position(10, 0, FACING.NORTH);
        assertFalse(s.isValidPosition(p));
        p = new Position(0, -3, FACING.NORTH);
        assertFalse(s.isValidPosition(p));
        p = new Position(0, 10, FACING.NORTH);
        assertFalse(s.isValidPosition(p));
    }

}