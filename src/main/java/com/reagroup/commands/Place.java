package com.reagroup.commands;

import com.reagroup.beans.Position;
import com.reagroup.beans.Robot;
import com.reagroup.constants.FACING;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class Place extends Command {

    public Place(String commandString) {
        super(commandString);
    }

    @Override
    public void execute(Robot robot) throws Exception {
        String[] parameters = this.parseParameters();
        int x = this.parseX(parameters);
        int y = this.parseY(parameters);
        FACING facing = this.parseFacing(parameters);
        robot.setPosition(new Position(x, y));
        robot.setFacing(facing);
    }

    private String[] parseParameters() throws Exception {
        String tmp = this.getCommandString().substring(1 + this.getCommandString().indexOf(' '));
        String[] parameters = tmp.split(",");
        if (parameters.length != 3) {
            throw new Exception("The PLACE command requires 3 parameters.");
        }
        return parameters;
    }

    private int parseX(String[] parameters) throws Exception {
        int x;
        try {
            x = Integer.parseInt(parameters[0]);
        } catch (NumberFormatException e) {
            throw new Exception("Please provide a valid number for the x coordinate.");
        }
        return x;
    }

    private int parseY(String[] parameters) throws Exception {
        int y;
        try {
            y = Integer.parseInt(parameters[1]);
        } catch (NumberFormatException e) {
            throw new Exception("Please provide a valid number for the y coordinate.");
        }
        return y;
    }

    private FACING parseFacing(String[] parameters) throws Exception {
        FACING facing;
        try {
            facing = FACING.valueOf(parameters[2].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Please provide a valid facing direction.");
        }
        return facing;
    }

}