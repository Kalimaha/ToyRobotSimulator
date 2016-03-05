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
    }

    public void testExecute() {
        String[] commands = {"PLACE 0,0,NORTH", "MOVE", "REPORT"};
        try {
            s.execute(commands);
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
            p = s.parsePosition(command);
        } catch (Exception e) {
            assertEquals("X is not a valid coordinate.", e.getMessage());
        }
        command = "PARSE 0,z,NORTH";
        try {
            p = s.parsePosition(command);
        } catch (Exception e) {
            assertEquals("Y is not a valid coordinate.", e.getMessage());
        }
        command = "PARSE 0,0,z";
        try {
            p = s.parsePosition(command);
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

}