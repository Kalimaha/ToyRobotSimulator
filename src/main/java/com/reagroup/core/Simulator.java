package com.reagroup.core;

import com.reagroup.beans.Position;
import com.reagroup.beans.Table;
import com.reagroup.constants.COMMAND;
import com.reagroup.constants.FACING;

import java.util.Arrays;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class Simulator {

    private Position position;

    private Table table;

    public Simulator() {
        this.setTable(new Table());
        this.setPosition(new Position(0, 0, FACING.NORTH));
    }

    public void execute(String[] commands) throws Exception {

        /* The application should discard all commands in the sequence until a valid PLACE command has been executed. */
        commands = cleanCommands(commands);

        /* Iterate over commands. */
        for (String command : commands) {

            /* MOVE, LEFT, RIGHT and REPORT have no arguments. */
            try {
                switch (COMMAND.valueOf(command.toUpperCase())) {
                    case MOVE:
                        break;
                    case LEFT:
                        break;
                    case RIGHT:
                        break;
                    case REPORT:
                        break;
                }
            }

            /* Parse the arguments for the PLACE command. */
            catch (IllegalArgumentException e) {
                place(parsePosition(command));
            }

        }

    }

    public Position parsePosition(String command) throws Exception {
        String tmp = command.substring(1 + command.indexOf(' '));
        String[] p = tmp.split(",");
        int x, y;
        FACING f;
        try {
            x = Integer.parseInt(p[0]);
        } catch (NumberFormatException e) {
            throw new Exception("X is not a valid coordinate.");
        }
        try {
            y = Integer.parseInt(p[1]);
        } catch (NumberFormatException e) {
            throw new Exception("Y is not a valid coordinate.");
        }
        try {
            f = FACING.valueOf(p[2].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("FACING is not a valid value.");
        }
        return new Position(x, y, FACING.valueOf(p[2].toUpperCase()));
    }

    /* The application should discard all commands in the sequence until a valid PLACE command has been executed. */
    public String[] cleanCommands(String[] commands) {
        for (int i = 0; i < commands.length; i += 1) {
            if (commands[i].toUpperCase().startsWith("PLACE")) {
                return Arrays.copyOfRange(commands, i, commands.length);
            }
        }
        return null;
    }

    public void place(Position p) {
        this.setPosition(p);
    }

    public void move() {
        switch (this.getPosition().getFacing()) {
            case EAST:
                if (this.getPosition().getX() + 1 < this.getTable().getWidth()) {
                    this.getPosition().setX(this.getPosition().getX() + 1);
                }
                break;
            case WEST:
                if (this.getPosition().getX() - 1 > -1) {
                    this.getPosition().setX(this.getPosition().getX() - 1);
                }
                break;
            case NORTH:
                if (this.getPosition().getY() + 1 < this.getTable().getHeight()) {
                    this.getPosition().setY(this.getPosition().getY() + 1);
                }
                break;
            case SOUTH:
                if (this.getPosition().getY() - 1 > -1) {
                    this.getPosition().setY(this.getPosition().getY() - 1);
                }
                break;
        }
    }

    public void left() {
        switch (this.getPosition().getFacing()) {
            case NORTH:
                this.getPosition().setFacing(FACING.WEST);
                break;
            case SOUTH:
                this.getPosition().setFacing(FACING.EAST);
                break;
            case EAST:
                this.getPosition().setFacing(FACING.NORTH);
                break;
            case WEST:
                this.getPosition().setFacing(FACING.SOUTH);
                break;
        }
    }

    public void right() {
        switch (this.getPosition().getFacing()) {
            case NORTH:
                this.getPosition().setFacing(FACING.EAST);
                break;
            case SOUTH:
                this.getPosition().setFacing(FACING.WEST);
                break;
            case EAST:
                this.getPosition().setFacing(FACING.SOUTH);
                break;
            case WEST:
                this.getPosition().setFacing(FACING.NORTH);
                break;
        }
    }

    public String report() {
        return "X: " + this.getPosition().getX() +
               ", Y: " + this.getPosition().getY() +
               ", FACING: " + this.getPosition().getFacing();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

}