package com.reagroup.commands;

import com.reagroup.beans.Position;
import com.reagroup.beans.Robot;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class Report extends Command {

    public Report(String commandString) {
        super(commandString);
    }

    @Override
    public void execute(Robot robot) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(robot.getPosition().getX());
        sb.append(",");
        sb.append(robot.getPosition().getY());
        sb.append(",");
        sb.append(robot.getFacing().name());
        System.out.println(sb);
    }

}