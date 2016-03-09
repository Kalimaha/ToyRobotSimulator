package com.reagroup.commands;

import com.reagroup.beans.Position;
import com.reagroup.beans.Robot;
import com.reagroup.constants.FACING;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class Left extends Command {

    public Left(String commandString) {
        super(commandString);
    }

    @Override
    public void execute(Robot robot) throws Exception {
        switch (robot.getFacing()) {
            case NORTH:
                robot.setFacing(FACING.WEST);
                break;
            case SOUTH:
                robot.setFacing(FACING.EAST);
                break;
            case EAST:
                robot.setFacing(FACING.NORTH);
                break;
            case WEST:
                robot.setFacing(FACING.SOUTH);
                break;
        }
    }

}