package com.reagroup.commands;

import com.reagroup.beans.Robot;
import com.reagroup.constants.FACING;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class Right extends Command {

    public Right(String commandString) {
        super(commandString);
    }

    @Override
    public void execute(Robot robot) throws Exception {
        switch (robot.getFacing()) {
            case NORTH:
                robot.setFacing(FACING.EAST);
                break;
            case SOUTH:
                robot.setFacing(FACING.WEST);
                break;
            case EAST:
                robot.setFacing(FACING.SOUTH);
                break;
            case WEST:
                robot.setFacing(FACING.NORTH);
                break;
        }
    }

}