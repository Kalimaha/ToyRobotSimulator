package com.reagroup.commands;

import com.reagroup.beans.Position;
import com.reagroup.beans.Robot;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class Move extends Command {

    public Move(String commandString) {
        super(commandString);
    }

    @Override
    public void execute(Robot robot) throws Exception {
        switch (robot.getFacing()) {
            case NORTH:
                robot.setPosition(new Position(robot.getPosition().getX(), robot.getPosition().getY() + 1));
                break;
            case SOUTH:
                robot.setPosition(new Position(robot.getPosition().getX(), robot.getPosition().getY() - 1));
                break;
            case EAST:
                robot.setPosition(new Position(robot.getPosition().getX() + 1, robot.getPosition().getY()));
                break;
            case WEST:
                robot.setPosition(new Position(robot.getPosition().getX() - 1, robot.getPosition().getY()));
                break;
        }
    }

}